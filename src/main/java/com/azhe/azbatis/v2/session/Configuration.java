package com.azhe.azbatis.v2.session;

import com.azhe.azbatis.v2.annotation.Entity;
import com.azhe.azbatis.v2.annotation.Select;
import com.azhe.azbatis.v2.binding.MapperRegistry;
import com.azhe.azbatis.v2.boot.MyBatisBoot;
import com.azhe.azbatis.v2.executor.CacheExecutor;
import com.azhe.azbatis.v2.executor.Executor;
import com.azhe.azbatis.v2.executor.SimpleExecutor;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Description: 获取并记录全局配置
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 12:35 下午
 * @since V1.0.0
 */
public class Configuration {

    public static final ResourceBundle SQL_MAPPINGS;  // sql全局映射关系配置
    public static final ResourceBundle PROPERTIES;    // 全局配置

    private Map<String, String> mapperStatements = new HashMap<>();  // 用于保存statementId 和 sql的关系
    private MapperRegistry mapperRegistry = new MapperRegistry();  // 保存mapper和代理工厂的关系

    private final List<Class<?>> mapperList = new ArrayList<>();  // 存放mapper类
    private final List<String> classPaths = new ArrayList<>();  // 存放路径下的所有class文件

    static {
        SQL_MAPPINGS = ResourceBundle.getBundle("sql");
        PROPERTIES = ResourceBundle.getBundle("azbatis");
    }

    /**
     * 构造函数初始化时，解析全局配置文件
     */
    public Configuration() {
        // Note：在properties和注解中重复配置SQL会覆盖
        // 1.解析sql
        for (String key : SQL_MAPPINGS.keySet()) {  // 解析
            String value = SQL_MAPPINGS.getString(key);
            String sql = value.split("--")[0];  // sql语句
            String pojoStr = value.split("--")[1];  // 返回对象
            String mapperClassStr = key.substring(0, key.lastIndexOf("."));  // mapper对象
            Class<?> clazz = null;  // mapper接口
            Class<?> pojoClass = null;  // 返回对象的类
            try {
                clazz = Class.forName(mapperClassStr);
                pojoClass = Class.forName(pojoStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // 2.解析注解
        //  ————> 1.扫描路径下的所有class文件放进容器   ——> 2.扫描所有符合的mapper解析保存
        String mapperPath = PROPERTIES.getString("mapper.path");
        scanPackage(mapperPath);  // 扫描包，将包的所有类存放到mapperList中
        for (Class<?> mapperClass : mapperList) {
            parsingClass(mapperClass);  // 解析mapperClass
        }

    }

    /**
     * 创建一个执行器
     * @return
     */
    public Executor newExecutor() {
        // 默认用一级缓存
        Executor executor;
        if (PROPERTIES.containsKey("cache.enabled") && PROPERTIES.getString("cache.enabled").equals("false")) {
            executor = new SimpleExecutor();
        } else {
            executor = new CacheExecutor(new SimpleExecutor());
        }
        return executor;
    }

    /**
     * 通过statementId获得sql
     * @param statementId
     * @return
     */
    public String getMappedStatement(String statementId) {
        return mapperStatements.get(statementId);
    }

    /**
     * 判断是否存在statementId
     * @param statementId
     * @return
     */
    public boolean hasStatementId(String statementId) {
        return mapperStatements.containsKey(statementId);
    }

    /**
     * 获得mapper接口代理
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        return mapperRegistry.getMapper(clazz, sqlSession);
    }

    /**
     * 扫描路径下的所有class文件
     * @param mapperPath
     */
    private void scanPackage(String mapperPath) {
        String path = this.getClass().getResource("/").getPath();  // 根路径
        mapperPath = path + mapperPath.replace(".", File.separator);  // mapper文件的路径
        doScan(new File(mapperPath));
        for (String classPath : classPaths) {  // 遍历classPaths里的所有路径
            if (classPath.startsWith(File.separator)) {  // 统一路径
                classPath = classPath.replace("/", "\\").replaceFirst("\\\\", "");
            } else {
                classPath = classPath.replace("/", "\\");
            }
            // 转换并匹配获得 className
            classPath = classPath.replace(path.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".").replace(".class", "");
            Class<?> clazz = null;
            try {
                clazz = Class.forName(classPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (clazz.isInterface()) {
                mapperList.add(clazz);
            }
        }
    }

    /**
     * 将路径下的class文件的路径存放进list
     * @param mapperFile
     */
    private void doScan(File mapperFile) {
        if (mapperFile.isDirectory()) {  // 文件夹
            File[] files = mapperFile.listFiles();
            for (File file : files) {
                doScan(file);
            }
        } else {
            if (mapperFile.getName().endsWith(".class")) {
                classPaths.add(mapperFile.getPath());
            }
        }
    }

    /**
     * 解析mapper类
     * @param mapperClass
     */
    private void parsingClass(Class<?> mapperClass) {
        // 实体注解
        if (mapperClass.isAnnotationPresent(Entity.class)) {
            Entity annotation = mapperClass.getAnnotation(Entity.class);
            Class<?> pojoClass = annotation.value();
            mapperRegistry.addMapper(mapperClass, pojoClass);
        }

        // 方法注解解析
        Method[] methods = mapperClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Select.class)) {
                Select annotation = method.getAnnotation(Select.class);
                mapperStatements.put(mapperClass.getName() + "." + method.getName(), annotation.value());
            }
        }
    }
}

package com.azhe.azbatis.v2.executor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: 封装结果集的处理，4大对象之一
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 5:24 下午
 * @since V1.0.0
 */
public class ResultSetHandler {

    /**
     * 处理结果
     * @param resultSet
     * @param clazz
     * @param <T>
     */
    public <T> T handler(ResultSet resultSet, Class<T> clazz) {
        T pojo = null;
        try {
            pojo = clazz.newInstance();
            if (resultSet.next()) {
                for (Field fields : clazz.getDeclaredFields()) {
                    // 设置参数
                    setValue(pojo, fields, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pojo;
    }

    /**
     * 设置参数
     * @param pojo
     * @param fields
     * @param resultSet
     */
    private void setValue(Object pojo, Field fields, ResultSet resultSet) {
        try {
            Method writeMethod = pojo.getClass().getMethod("set" + resultSetHandler(fields.getName()), fields.getType());
            writeMethod.invoke(pojo, getResult(resultSet, fields));  // 调用写方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得结果集对应的字段的数据
     * @param resultSet
     * @param fields
     * @return
     */
    private Object getResult(ResultSet resultSet, Field fields) throws SQLException {
        String columnName = humpToUnderline(fields.getName());  // 字段名
        if (fields.getType() == Integer.class) {
            return resultSet.getInt(columnName);
        } else if (fields.getType() == Boolean.class) {
            return resultSet.getBoolean(columnName);
        } else if (fields.getType() == Long.class) {
            return resultSet.getLong(columnName);
        } else if (fields.getType() == String.class) {
            return resultSet.getString(columnName);
        } else if (fields.getType() == Double.class) {
            return resultSet.getDouble(columnName);
        } else if (fields.getType() == BigDecimal.class) {
            return resultSet.getBigDecimal(columnName);
        }
        return resultSet.getString(columnName);
    }

    /**
     * 首字母大写
     * @param fieldName
     * @return
     */
    private String resultSetHandler(String fieldName) {
        char[] nameChars = fieldName.toCharArray();
        nameChars[0] -= 32;
        return new String(nameChars);
    }

    /**
     * 获得属性对应的数据库字段名，驼峰命名法转下划线规则
     * @param fieldName
     * @return
     */
    private String humpToUnderline(String fieldName) {
        StringBuilder sb = new StringBuilder(fieldName);
        int temp = 0;  // 记录插入后后边数据的后移数
        if (!fieldName.contains("_")) {
            for (int i = 0; i < fieldName.length(); i++) {
                if (Character.isUpperCase(fieldName.charAt(i))) {  // 找到大写的字母
                    sb.insert(temp + i, "_");
                    temp ++;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        ResultSetHandler rs = new ResultSetHandler();
        String abcDefFg = rs.humpToUnderline("abcDefFg");
        System.out.println(abcDefFg);
    }

}

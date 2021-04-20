package com.azhe.azbatis.v2.parameter;

import java.sql.PreparedStatement;

/**
 * Description: 参数处理器，4大对象之一
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 5:44 下午
 * @since V1.0.0
 */
public class ParameterHandler {

    // 带预编译的jdbc执行
    private PreparedStatement pstm;

    public ParameterHandler(PreparedStatement pstm) {
        this.pstm = pstm;
    }

    /**
     * 设置参数
     * @param parameters
     */
    public void setParameters(Object[] parameters) {
        try {
            // PreparedStatement 是从序号1开始
            for (int i = 0; i < parameters.length ; i++) {
                int k = i + 1;
                if (parameters[i] instanceof Integer) {
                    pstm.setInt(k, (Integer)parameters[i]);
                } else if (parameters[i] instanceof Long) {
                    pstm.setLong(k, (Long)parameters[i]);
                } else if (parameters[i] instanceof String) {
                    pstm.setString(k, String.valueOf(parameters[i]));
                } else if (parameters[i] instanceof Boolean) {
                    pstm.setBoolean(k, (Boolean) parameters[i]);
                } else {
                    pstm.setString(k, String.valueOf(parameters[i]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

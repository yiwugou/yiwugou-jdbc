package com.yiwugou.jdbc.core.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 * IoUtils
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月10日 下午3:24:26
 */
public class IoUtils {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                //                e.printStackTrace();
            }
        }
    }
}

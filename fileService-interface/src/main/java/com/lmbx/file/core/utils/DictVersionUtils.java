package com.lmbx.file.core.utils;

import java.time.LocalDate;

/**
 * Description:
 * Author: huangchengzhi
 * Create: 2017/7/11
 */
public class DictVersionUtils {

    /**
     * 生成新的版本号
     * @param oldDictVersion
     * @return
     */
    public static String getDictVersion(String oldDictVersion) {
        int newIndex = 1;
        String currentDateStr = LocalDate.now().toString().replace("-", "");

        if (oldDictVersion != null) {
            String dateStr = oldDictVersion.substring(0, oldDictVersion.length() - 4);
            if (currentDateStr.equals(dateStr)) {
                String index = oldDictVersion.substring(oldDictVersion.length() - 4);
                newIndex = Integer.parseInt(index) + 1;
            }
        }

        String indexStr = String.format("%04d", newIndex);

        return currentDateStr + indexStr;
    }

}

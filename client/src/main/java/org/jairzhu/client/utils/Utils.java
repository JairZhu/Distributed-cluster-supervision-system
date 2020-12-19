package org.jairzhu.client.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Random;

public class Utils {
    public static String getRandomName() {
        StringBuilder stringBuilder = new StringBuilder("");
        Random random = new Random();
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int count = 0;
        while (count < 8) {
            int i = Math.abs(random.nextInt(str.length));
            stringBuilder.append(str[i]);
            count++;
        }
        return stringBuilder.toString();
    }

    public static int getCPUNumber() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getAvailableProcessors();
    }

    public static String getOS() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getName();
    }

    public static double getAverageLoad() {
        Random random = new Random();
        return random.nextInt(100);
    }
}

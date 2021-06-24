package com.will.mvvmplug.utils;

import com.will.mvvmplug.Const;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchToolUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static final char CHAR_A = 'a';
    private static final char CHAR_Z = 'z';

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String lineToHumpFirstChar(String str) {
        String target = lineToHump(str);
        return upperFirstLatter(target);
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, Const.UNDER_LINE + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        String content = sb.toString();
        if (content.startsWith(Const.UNDER_LINE)) {
            content = content.replaceFirst(Const.UNDER_LINE, "");
        }
        return content;
    }

    /**
     * 首字母大写的下划线
     */
    public static String humpToLineWithFirstChar(String str) {
        String content = humpToLine2(str);
        return upperFirstLatter(content);
    }


    /**
     * 方法一
     *
     * @param letter
     * @return
     */
    static public String upperFirstLatter(String letter) {
        if (letter == null || letter.isEmpty()) {
            return "";
        }
        char[] chars = letter.toCharArray();
        if (chars[0] >= CHAR_A && chars[0] <= CHAR_Z) {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    /**
     * 方法二
     *
     * @param letter
     * @return
     */
    static public String upperFirstLatter2(String letter) {
        return letter.substring(0, 1).toUpperCase() + letter.substring(1);
    }

}

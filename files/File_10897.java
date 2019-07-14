package com.vondear.rxtool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vondear.rxtool.RxConstTool.REGEX_CHZ;
import static com.vondear.rxtool.RxConstTool.REGEX_DATE;
import static com.vondear.rxtool.RxConstTool.REGEX_EMAIL;
import static com.vondear.rxtool.RxConstTool.REGEX_IDCARD;
import static com.vondear.rxtool.RxConstTool.REGEX_IDCARD15;
import static com.vondear.rxtool.RxConstTool.REGEX_IDCARD18;
import static com.vondear.rxtool.RxConstTool.REGEX_IP;
import static com.vondear.rxtool.RxConstTool.REGEX_MOBILE_EXACT;
import static com.vondear.rxtool.RxConstTool.REGEX_MOBILE_SIMPLE;
import static com.vondear.rxtool.RxConstTool.REGEX_TEL;
import static com.vondear.rxtool.RxConstTool.REGEX_URL;
import static com.vondear.rxtool.RxConstTool.REGEX_USERNAME;
import static com.vondear.rxtool.RxDataTool.isNullString;

/**
 * @author Vondear
 * @date 2017/3/15
 */

public class RxRegTool {
    //--------------------------------------------正则表达�?-----------------------------------------
    /**
     * 原文链接：http://caibaojian.com/regexp-example.html
     * �??�?�信�?�中的网络链接:(h|H)(r|R)(e|E)(f|F) *= *('|")?(\w|\\|\/|\.)+('|"| *|>)?
     * �??�?�信�?�中的邮件地�?�:\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
     * �??�?�信�?�中的图片链接:(s|S)(r|R)(c|C) *= *('|")?(\w|\\|\/|\.)+('|"| *|>)?
     * �??�?�信�?�中的IP地�?�:(\d+)\.(\d+)\.(\d+)\.(\d+)
     * �??�?�信�?�中的中国电�?�?��?（包括移动和固定电�?）:(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}
     * �??�?�信�?�中的中国邮政编�?:[1-9]{1}(\d+){5}
     * �??�?�信�?�中的中国身份�?�?��?:\d{18}|\d{15}
     * �??�?�信�?�中的整数：\d+
     * �??�?�信�?�中的浮点数（�?��?数）：(-?\d*)\.?\d+
     * �??�?�信�?�中的任何数字 ：(-?\d*)(\.\d+)?
     * �??�?�信�?�中的中文字符串：[\u4e00-\u9fa5]*
     * �??�?�信�?�中的�?�字节字符串 (汉字)：[^\x00-\xff]*
     */


    /**
     * 判断是�?�为真实手机�?�
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验�?银�?��?��?�
     *
     * @param cardNo
     * @return
     */
    public static boolean isBankCard(String cardNo) {
        Pattern p = Pattern.compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$");
        Matcher m = p.matcher(cardNo);
        return m.matches();
    }

    /**
     * 15�?和18�?身份�?�?��?的正则表达�? 身份�?验�?
     *
     * @param idCard
     * @return
     */
    public static boolean validateIdCard(String idCard) {
        // 15�?和18�?身份�?�?��?的正则表达�?
        String regIdCard = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";
        Pattern p = Pattern.compile(regIdCard);
        return p.matcher(idCard).matches();
    }
    //=========================================正则表达�?=============================================

    /**
     * 验�?手机�?�（简�?�）
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isMobileSimple(String string) {
        return isMatch(REGEX_MOBILE_SIMPLE, string);
    }

    /**
     * 验�?手机�?�（精确）
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isMobileExact(String string) {
        return isMatch(REGEX_MOBILE_EXACT, string);
    }

    /**
     * 验�?电�?�?��?
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isTel(String string) {
        return isMatch(REGEX_TEL, string);
    }

    /**
     * 验�?身份�?�?��?15�?
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isIDCard15(String string) {
        return isMatch(REGEX_IDCARD15, string);
    }

    /**
     * 验�?身份�?�?��?18�?
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isIDCard18(String string) {
        return isMatch(REGEX_IDCARD18, string);
    }

    /**
     * 验�?身份�?�?��?15或18�? 包�?�以x结尾
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isIDCard(String string) {
        return isMatch(REGEX_IDCARD, string);
    }


    /*********************************** 身份�?验�?开始 ****************************************/
    /**
     * 身份�?�?��?验�? 1�?�?��?的结构 公民身份�?��?是特�?组�?��?，由�??七�?数字本体�?和一�?校验�?组�?。排列顺�?从左至�?��?次为：六�?数字地�?��?，
     * 八�?数字出生日期�?，三�?数字顺�?�?和一�?数字校验�?。 2�?地�?��?(�?六�?数）
     * 表示编�?对象常�?户�?�所在县(市�?旗�?区)的行政区划代�?，按GB/T2260的规定执行。 3�?出生日期�?（第七�?至�??四�?）
     * 表示编�?对象出生的年�?月�?日，按GB/T7408的规定执行，年�?月�?日代�?之间�?用分隔符。 4�?顺�?�?（第�??五�?至�??七�?）
     * 表示在�?�一地�?��?所标识的区域范围内，对�?�年�?�?�月�?�?�日出生的人编定的顺�?�?�， 顺�?�?的奇数分�?给男性，�?�数分�?给女性。 5�?校验�?（第�??八�?数）
     * （1）�??七�?数字本体�?加�?�求和公�? S = Sum(Ai * Wi), i = 0, ... , 16 ，先对�?17�?数字的�?�求和
     * Ai:表示第i�?置上的身份�?�?��?数字值 Wi:表示第i�?置上的加�?�因�? Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验�? Y: 0 1 2 3 4 5 6 7 8 9 10 校验�?: 1 0 X 9 8 7 6 5 4 3 2
     */

    /**
     * 功能：身份�?的有效验�?
     *
     * @param IDStr 身份�?�?�
     * @return 有效：返回"有效" 无效：返回String信�?�
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public static String IDCardValidate(String IDStr) {
        String errorInfo = "";// 记录错误信�?�
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ �?��?的长度 15�?或18�? ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份�?�?��?长度应该为15�?或18�?。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最�?�以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份�?15�?�?��?都应为数字 ; 18�?�?��?除最�?�一�?外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是�?�有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份�?生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份�?生日�?在有效范围。";
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份�?月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份�?日期无效";
            return errorInfo;
        }
        // =====================(end)=====================

        // ================ 地区�?时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份�?地区编�?错误。";
            return errorInfo;
        }
        // ==============================================

        // ================ 判断最�?�一�?的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份�?无效，�?是�?�法的身份�?�?��?";
                return errorInfo;
            }
        } else {
            return "有效";
        }
        // =====================(end)=====================
        return "有效";
    }

    /**
     * 功能：设置地区编�?
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙�?�");
        hashtable.put("21", "辽�?");
        hashtable.put("22", "�?�林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江�?");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "�?建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河�?�");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖�?�");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海�?�");
        hashtable.put("50", "�?庆");
        hashtable.put("51", "四�?");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云�?�");
        hashtable.put("54", "西�?");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "�?�海");
        hashtable.put("64", "�?�?");
        hashtable.put("65", "新疆");
        hashtable.put("71", "�?�湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是�?�为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 验�?邮箱
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }

    /**
     * 验�?URL
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isURL(String string) {
        return isMatch(REGEX_URL, string);
    }

    /**
     * 验�?汉字
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isChz(String string) {
        return isMatch(REGEX_CHZ, string);
    }

    /**
     * 验�?用户�??
     * <p>�?�值范围为a-z,A-Z,0-9,"_",汉字，�?能以"_"结尾,用户�??必须是6-20�?</p>
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isUsername(String string) {
        return isMatch(REGEX_USERNAME, string);
    }

    /**
     * 验�?yyyy-MM-dd格�?的日期校验，已考虑平闰年
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isDate(String string) {
        return isMatch(REGEX_DATE, string);
    }

    /**
     * 验�?IP地�?�
     *
     * @param string 待验�?文本
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isIP(String string) {
        return isMatch(REGEX_IP, string);
    }

    /**
     * string是�?�匹�?regex正则表达�?字符串
     *
     * @param regex  正则表达�?字符串
     * @param string �?匹�?的字符串
     * @return {@code true}: 匹�?<br>{@code false}: �?匹�?
     */
    public static boolean isMatch(String regex, String string) {
        return !isNullString(string) && Pattern.matches(regex, string);
    }

    /**
     * 验�?固定电�?�?��?
     *
     * @param phone 电�?�?��?，格�?：国家（地区）电�?代�? + 区�?�（城市代�?） + 电�?�?��?，如：+8602085588447
     *              <p><b>国家（地区） 代�? ：</b>标识电�?�?��?的国家（地区）的标准国家（地区）代�?。它包�?�从 0 到 9 的一�?或多�?数字，
     *              数字之�?�是空格分隔的国家（地区）代�?。</p>
     *              <p><b>区�?�（城市代�?）：</b>这�?�能包�?�一个或多个从 0 到 9 的数字，地区或城市代�?放在圆括�?�——
     *              对�?使用地区或城市代�?的国家（地区），则�?略该组件。</p>
     *              <p><b>电�?�?��?：</b>这包�?�从 0 到 9 的一个或多个数字 </p>
     * @return 验�?�?功返回true，验�?失败返回false
     */
    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

    /**
     * 验�?整数（正整数和负整数）
     *
     * @param digit 一�?或多�?0-9之间的整数
     * @return 验�?�?功返回true，验�?失败返回false
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex, digit);
    }

    /**
     * 验�?整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一�?或多�?0-9之间的浮点数，如：1.23，233.30
     * @return 验�?�?功返回true，验�?失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 验�?空白字符
     *
     * @param blankSpace 空白字符，包括：空格�?\t�?\n�?\r�?\f�?\x0B
     * @return 验�?�?功返回true，验�?失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
     * 验�?日期（年月日）
     *
     * @param birthday 日期，格�?：1992-09-03，或1992.09.03
     * @return 验�?�?功返回true，验�?失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 匹�?中国邮政编�?
     *
     * @param postcode 邮政编�?
     * @return 验�?�?功返回true，验�?失败返回false
     */
    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }
}

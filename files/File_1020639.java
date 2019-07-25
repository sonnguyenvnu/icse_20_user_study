package com.github.vole.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.vole.common.constants.SensitiveInfo;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;


/**
 * æ•?æ„Ÿä¿¡æ?¯å±?è”½å·¥å…·
 */
public final class SensitiveInfoUtils {


    /**
     * [ä¸­æ–‡å§“å??] å?ªæ˜¾ç¤ºç¬¬ä¸€ä¸ªæ±‰å­—ï¼Œå…¶ä»–éš?è—?ä¸º2ä¸ªæ˜Ÿå?·<ä¾‹å­?ï¼šæ?Ž**>
     *
     * @param fullName
     * @return
     */
    public static String chineseName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * [ä¸­æ–‡å§“å??] å?ªæ˜¾ç¤ºç¬¬ä¸€ä¸ªæ±‰å­—ï¼Œå…¶ä»–éš?è—?ä¸º2ä¸ªæ˜Ÿå?·<ä¾‹å­?ï¼šæ?Ž**>
     *
     * @param familyName
     * @param givenName
     * @return
     */
    public static String chineseName(String familyName, String givenName) {
        if (StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {
            return "";
        }
        return chineseName(familyName + givenName);
    }

    /**
     * [èº«ä»½è¯?å?·] æ˜¾ç¤ºæœ€å?Žå››ä½?ï¼Œå…¶ä»–éš?è—?ã€‚å…±è®¡18ä½?æˆ–è€…15ä½?ã€‚<ä¾‹å­?ï¼š*************5762>
     *
     * @param id
     * @return
     */
    public static String idCardNum(String id) {
        if (StringUtils.isBlank(id)) {
            return "";
        }
        String num = StringUtils.right(id, 4);
        return StringUtils.leftPad(num, StringUtils.length(id), "*");
    }

    /**
     * [å›ºå®šç”µè¯?] å?Žå››ä½?ï¼Œå…¶ä»–éš?è—?<ä¾‹å­?ï¼š****1234>
     *
     * @param num
     * @return
     */
    public static String fixedPhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
    }

    /**
     * [æ‰‹æœºå?·ç ?] å‰?ä¸‰ä½?ï¼Œå?Žå››ä½?ï¼Œå…¶ä»–éš?è—?<ä¾‹å­?:138******1234>
     *
     * @param num
     * @return
     */
    public static String mobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
    }

    /**
     * [åœ°å?€] å?ªæ˜¾ç¤ºåˆ°åœ°åŒºï¼Œä¸?æ˜¾ç¤ºè¯¦ç»†åœ°å?€ï¼›æˆ‘ä»¬è¦?å¯¹ä¸ªäººä¿¡æ?¯å¢žå¼ºä¿?æŠ¤<ä¾‹å­?ï¼šåŒ—äº¬å¸‚æµ·æ·€åŒº****>
     *
     * @param address
     * @param sensitiveSize
     *            æ•?æ„Ÿä¿¡æ?¯é•¿åº¦
     * @return
     */
    public static String address(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [ç”µå­?é‚®ç®±] é‚®ç®±å‰?ç¼€ä»…æ˜¾ç¤ºç¬¬ä¸€ä¸ªå­—æ¯?ï¼Œå‰?ç¼€å…¶ä»–éš?è—?ï¼Œç”¨æ˜Ÿå?·ä»£æ›¿ï¼Œ@å?Šå?Žé?¢çš„åœ°å?€æ˜¾ç¤º<ä¾‹å­?:g**@163.com>
     *
     * @param email
     * @return
     */
    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1)
            return email;
        else
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
    }

    /**
     * [é“¶è¡Œå?¡å?·] å‰?å…­ä½?ï¼Œå?Žå››ä½?ï¼Œå…¶ä»–ç”¨æ˜Ÿå?·éš?è—?æ¯?ä½?1ä¸ªæ˜Ÿå?·<ä¾‹å­?:6222600**********1234>
     *
     * @param cardNum
     * @return
     */
    public static String bankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * [å…¬å?¸å¼€æˆ·é“¶è¡Œè?”å?·] å…¬å?¸å¼€æˆ·é“¶è¡Œè?”è¡Œå?·,æ˜¾ç¤ºå‰?ä¸¤ä½?ï¼Œå…¶ä»–ç”¨æ˜Ÿå?·éš?è—?ï¼Œæ¯?ä½?1ä¸ªæ˜Ÿå?·<ä¾‹å­?:12********>
     *
     * @param code
     * @return
     */
    public static String cnapsCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }

    /**
     * èŽ·å?–è„±æ•?jsonä¸² <æ³¨æ„?ï¼šé€’å½’å¼•ç”¨ä¼šå¯¼è‡´java.lang.StackOverflowError>
     *
     * @param javaBean
     * @return
     */
    public static String getJson(Object javaBean) {
        String json = null;
        if (null != javaBean) {
            Class<? extends Object> raw = javaBean.getClass();
            try {
                if (raw.isInterface())
                    return json;
                Gson g = new Gson();
                Object clone = g.fromJson(g.toJson(javaBean, javaBean.getClass()), javaBean.getClass());
                Set<Integer> referenceCounter = new HashSet<Integer>();
                SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(raw), clone, referenceCounter);
                json = JSON.toJSONString(clone, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
                referenceCounter.clear();
                referenceCounter = null;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    private static Field[] findAllField(Class<?> clazz) {
        Field[] fileds = clazz.getDeclaredFields();
        while (null != clazz.getSuperclass() && !Object.class.equals(clazz.getSuperclass())) {
            fileds = (Field[]) ArrayUtils.addAll(fileds, clazz.getSuperclass().getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fileds;
    }
    private static void replace(Field[] fields, Object javaBean, Set<Integer> referenceCounter) throws IllegalArgumentException, IllegalAccessException {
        if (null != fields && fields.length > 0) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (null != field && null != javaBean) {
                    Object value = field.get(javaBean);
                    if (null != value) {
                        Class<?> type = value.getClass();
                        // 1.å¤„ç?†å­?å±žæ€§ï¼ŒåŒ…æ‹¬é›†å?ˆä¸­çš„
                        if (type.isArray()) {
                            int len = Array.getLength(value);
                            for (int i = 0; i < len; i++) {
                                Object arrayObject = Array.get(value, i);
                                SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(arrayObject.getClass()), arrayObject, referenceCounter);
                            }
                        } else if (value instanceof Collection<?>) {
                            Collection<?> c = (Collection<?>) value;
                            Iterator<?> it = c.iterator();
                            while (it.hasNext()) {
                                Object collectionObj = it.next();
                                SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(collectionObj.getClass()), collectionObj, referenceCounter);
                            }
                        } else if (value instanceof Map<?, ?>) {
                            Map<?, ?> m = (Map<?, ?>) value;
                            Set<?> set = m.entrySet();
                            for (Object o : set) {
                                Entry<?, ?> entry = (Entry<?, ?>) o;
                                Object mapVal = entry.getValue();
                                SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(mapVal.getClass()), mapVal, referenceCounter);
                            }
                        } else if (!type.isPrimitive()
                            && !StringUtils.startsWith(type.getPackage().getName(), "javax.")
                            && !StringUtils.startsWith(type.getPackage().getName(), "java.")
                            && !StringUtils.startsWith(field.getType().getName(), "javax.")
                            && !StringUtils.startsWith(field.getName(), "java.")
                            && referenceCounter.add(value.hashCode())) {
                            SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(type), value, referenceCounter);
                        }
                    }
                    // 2. å¤„ç?†è‡ªèº«çš„å±žæ€§
                    SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
                    if (field.getType().equals(String.class) && null != annotation) {
                        String valueStr = (String) value;
                        if (StringUtils.isNotBlank(valueStr)) {
                            switch (annotation.type()) {
                                case CHINESE_NAME: {
                                    field.set(javaBean, SensitiveInfoUtils.chineseName(valueStr));
                                    break;
                                }
                                case ID_CARD: {
                                    field.set(javaBean, SensitiveInfoUtils.idCardNum(valueStr));
                                    break;
                                }
                                case FIXED_PHONE: {
                                    field.set(javaBean, SensitiveInfoUtils.fixedPhone(valueStr));
                                    break;
                                }
                                case MOBILE_PHONE: {
                                    field.set(javaBean, SensitiveInfoUtils.mobilePhone(valueStr));
                                    break;
                                }
                                case ADDRESS: {
                                    field.set(javaBean, SensitiveInfoUtils.address(valueStr, 4));
                                    break;
                                }
                                case EMAIL: {
                                    field.set(javaBean, SensitiveInfoUtils.email(valueStr));
                                    break;
                                }
                                case BANK_CARD: {
                                    field.set(javaBean, SensitiveInfoUtils.bankCard(valueStr));
                                    break;
                                }
                                case CNAPS_CODE: {
                                    field.set(javaBean, SensitiveInfoUtils.cnapsCode(valueStr));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    public static Method [] findAllMethod(Class<?> clazz){
        Method [] methods= clazz.getMethods();
        return methods;
    }

    //----------------------------------------------------------------------------------------------
    public static enum SensitiveType {
        /**
         * ä¸­æ–‡å??
         */
        CHINESE_NAME,

        /**
         * èº«ä»½è¯?å?·
         */
        ID_CARD,
        /**
         * åº§æœºå?·
         */
        FIXED_PHONE,
        /**
         * æ‰‹æœºå?·
         */
        MOBILE_PHONE,
        /**
         * åœ°å?€
         */
        ADDRESS,
        /**
         * ç”µå­?é‚®ä»¶
         */
        EMAIL,
        /**
         * é“¶è¡Œå?¡
         */
        BANK_CARD,
        /**
         * å…¬å?¸å¼€æˆ·é“¶è¡Œè?”å?·
         */
        CNAPS_CODE;
    }
}

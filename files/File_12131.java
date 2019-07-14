package com.geekq.admin.entity;

import com.geekq.common.utils.numcal.BitStatesUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

/**
 * @author é‚±æ¶¦æ³½
 */
@Getter
@Setter
@Alias("UserInfo")
public class Userinfo extends BaseDomain {

    private static final long serialVersionUID = -2194938919842714855L;
    /**
     * ç‰ˆæœ¬å?·
     */
    private int version;
    /**
     * ä½?çŠ¶æ€?
     */
    private Long bitState = 0L;
    /**
     * å¯¹åº”å®žå??è®¤è¯?ä¸­çš„çœŸå®žå§“å??
     */
    private String realName;
    /**
     * å¯¹åº”å®žå??è®¤è¯?ä¸­çš„èº«ä»½è¯?å?·
     */
    private String idNumber;
    /**
     * ç”¨æˆ·é‚®ç®±
     */
    private String email;
    /**
     *  æ‰‹æœºå?·
     */
    private String phoneNumber = "";
    /**
     * ç”¨æˆ·å½“å‰?è®¤è¯?åˆ†æ•°
     */
    private int authScore = 0;
    /**
     * ç”¨æˆ·æœ‰æ•ˆçš„å®žå??è®¤è¯?å¯¹è±¡
     */
    private Long realauthId;

    /**
     * ä¼šå‘˜çš„åŸºæœ¬èµ„æ–™
     * æœˆæ”¶å…¥
     */
    private SystemDictionaryItem incomeGrade;
    /**
     *  å©šå§»æƒ…å†µ
     */
    private SystemDictionaryItem marriage;
    /**
     * å­?å¥³æƒ…å†µ
     */
    private SystemDictionaryItem kidCount;
    /**
     * å­¦åŽ†
     */
    private SystemDictionaryItem educationBackground;
    /**
     * ä½?æˆ¿æ?¡ä»¶
     */
    private SystemDictionaryItem houseCondition;

    public static Userinfo empty(Long id) {
        Userinfo userinfo = new Userinfo();
		userinfo.setId(id);
		userinfo.setBitState(BitStatesUtils.OP_BASIC_INFO);
        return userinfo;
    }

    public void addState(Long state) {
        this.bitState = BitStatesUtils.addState(this.bitState, state);
    }

    public void removeState(Long state) {
        this.bitState = BitStatesUtils.removeState(this.bitState, state);
    }

    public boolean getIsBindPhone() {

        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_PHONE);
    }

    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_EMAIL);
    }

    public boolean getBaseInfo() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BASE_INFO);
    }

    public boolean getRealAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_REAL_AUTH);
    }

    public boolean getVedioAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_VEDIO_AUTH);
    }

    public boolean getHasBidRequest(){
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_HAS_BIDRQUEST);
    }

    /**
     * èŽ·å?–ç”¨æˆ·çœŸå®žå??å­—çš„éš?è—?å­—ç¬¦ä¸²ï¼Œå?ªæ˜¾ç¤ºå§“æ°?
     *
     * @param çœŸå®žå??å­—
     * @return
     */
    public String getAnonymousRealName() {
        if (StringUtils.hasLength(realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return realName;
    }

    /**
     * èŽ·å?–ç”¨æˆ·çœŸå®žå??å­—çš„éš?è—?å­—ç¬¦ä¸²ï¼Œå?ªæ˜¾ç¤ºå§“æ°?
     *
     * @param realName
     *            çœŸå®žå??å­—
     * @return
     */
    public static String getAnonymousRealName(String realName) {
        if (StringUtils.hasLength(realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return realName;
    }

    /**
     * èŽ·å?–ç”¨æˆ·èº«ä»½å?·ç ?çš„éš?è—?å­—ç¬¦ä¸²
     *
     * @param idNumber
     * @return
     */
    public static String getAnonymousIdNumber(String idNumber) {
        if (StringUtils.hasLength(idNumber)) {
            int len = idNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 5 && i < 10) || (i > len - 5)) {
                    replace += "*";
                } else {
                    replace += idNumber.charAt(i);
                }
            }
            return replace;
        }
        return idNumber;
    }


    /**
     * èŽ·å?–ç”¨æˆ·æ‰‹æœºå?·ç ?çš„éš?è—?å­—ç¬¦ä¸²
     *
     * @param phoneNumber
     *            ç”¨æˆ·æ‰‹æœºå?·ç ?
     * @return
     */
    public static String getAnonymousPhoneNumber(String phoneNumber) {
        if (StringUtils.hasLength(phoneNumber)) {
            int len = phoneNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if (i > 2 && i < 7) {
                    replace += "*";
                } else {
                    replace += phoneNumber.charAt(i);
                }
            }
            return replace;
        }
        return phoneNumber;
    }

    /**
     * èŽ·å?–ç”¨æˆ·ä½?å?€çš„éš?è—?å­—ç¬¦ä¸²
     *
     * @param currentAddress
     *            ç”¨æˆ·ä½?å?€
     * @return
     */
    public static String getAnonymousCurrentAddress(String currentAddress) {
        if (StringUtils.hasLength(currentAddress)
                && currentAddress.length() > 4) {
            String last = currentAddress.substring(currentAddress.length() - 4,
                    currentAddress.length());
            String stars = "";
            for (int i = 0; i < currentAddress.length() - 4; i++) {
                stars += "*";
            }
            return stars + last;
        }
        return currentAddress;
    }
}

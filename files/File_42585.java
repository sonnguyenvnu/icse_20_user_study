package com.roncoo.pay.trade.entity;

import com.roncoo.pay.common.core.entity.BaseEntity;

/**
 * �?微商户进件记录
 */
public class RpMicroSubmitRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务申请编�?�
     */
    private String businessCode;
    /**
     * �?微商户�?�
     */
    private String subMchId;
    /**
     * 身份�?人�?�?�照片，上传�?�的media_id
     */
    private String idCardCopy;
    /**
     * 身份�?国徽�?�照片，上传�?�的media_id
     */
    private String idCardNational;
    /**
     * 身份�?姓�??(开户�??称accountName�?�?�系人姓�??contact)
     */
    private String idCardName;
    /**
     * 身份�?�?��?
     */
    private String idCardNumber;
    /**
     * 身份�?有效期�?，格�?["1970-01-01","长期"]
     */
    private String idCardValidTime;
    /**
     * 开户银行(�?�?�人需�?�身份�?�??)
     */
    private String accountBank;
    /**
     * 开户银行�?市编�?（至少精确到市）
     */
    private String bankAddressCode;
    /**
     * 银行账�?�
     */
    private String accountNumber;
    /**
     * 门店�??称
     */
    private String storeName;
    /**
     * 门店�?市编�?
     */
    private String storeAddressCode;
    /**
     * 门店街�?��??称
     * 店铺详细地�?�，具体区/县�?�街�?�门牌�?�或大厦楼层，最长500个中文字符（无需填写�?市信�?�）
     */
    private String storeStreet;
    /**
     * 门店门�?�照片，上传�?�的media_id
     */
    private String storeEntrancePic;
    /**
     * 店内环境照片，上传�?�的media_id
     */
    private String indoorPic;
    /**
     * 商户简称 2~30个字符
     * 将在支付完�?页�?�买家展示，需与商家的实际�?�?�场景相符
     */
    private String merchantShortname;
    /**
     * 客�?电�?
     * 将在交易记录中�?�买家展示，请确�?电�?畅通以便微信回拨确认
     */
    private String servicePhone;
    /**
     * 售�?�商�?/�??供�?务�??述
     * �?饮/线下零售/居民生活�?务/休闲娱�?/交通出行/其他
     */
    private String productDesc;
    /**
     * 费率
     */
    private String rate;
    /**
     * �?�系人手机
     */
    private String contactPhone;
    
    
    /*------*/
    /**
     * 身份�?有效期
     */
    private String idCardValidTimeBegin;
    private String idCardValidTimeEnd;
    /*------*/

    public String getIdCardValidTimeBegin() {
        return idCardValidTimeBegin;
    }

    public void setIdCardValidTimeBegin(String idCardValidTimeBegin) {
        this.idCardValidTimeBegin = idCardValidTimeBegin;
    }

    public String getIdCardValidTimeEnd() {
        return idCardValidTimeEnd;
    }

    public void setIdCardValidTimeEnd(String idCardValidTimeEnd) {
        this.idCardValidTimeEnd = idCardValidTimeEnd;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getIdCardCopy() {
        return idCardCopy;
    }

    public void setIdCardCopy(String idCardCopy) {
        this.idCardCopy = idCardCopy;
    }

    public String getIdCardNational() {
        return idCardNational;
    }

    public void setIdCardNational(String idCardNational) {
        this.idCardNational = idCardNational;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardValidTime() {
        return idCardValidTime;
    }

    public void setIdCardValidTime(String idCardValidTime) {
        this.idCardValidTime = idCardValidTime;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getBankAddressCode() {
        return bankAddressCode;
    }

    public void setBankAddressCode(String bankAddressCode) {
        this.bankAddressCode = bankAddressCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddressCode() {
        return storeAddressCode;
    }

    public void setStoreAddressCode(String storeAddressCode) {
        this.storeAddressCode = storeAddressCode;
    }

    public String getStoreStreet() {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet) {
        this.storeStreet = storeStreet;
    }

    public String getStoreEntrancePic() {
        return storeEntrancePic;
    }

    public void setStoreEntrancePic(String storeEntrancePic) {
        this.storeEntrancePic = storeEntrancePic;
    }

    public String getIndoorPic() {
        return indoorPic;
    }

    public void setIndoorPic(String indoorPic) {
        this.indoorPic = indoorPic;
    }

    public String getMerchantShortname() {
        return merchantShortname;
    }

    public void setMerchantShortname(String merchantShortname) {
        this.merchantShortname = merchantShortname;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "RpMicroSubmitRecord{" +
                "businessCode='" + businessCode + '\'' +
                ", subMchId='" + subMchId + '\'' +
                ", idCardCopy='" + idCardCopy + '\'' +
                ", idCardNational='" + idCardNational + '\'' +
                ", idCardName='" + idCardName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", idCardValidTime='" + idCardValidTime + '\'' +
                ", accountBank='" + accountBank + '\'' +
                ", bankAddressCode='" + bankAddressCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeAddressCode='" + storeAddressCode + '\'' +
                ", storeStreet='" + storeStreet + '\'' +
                ", storeEntrancePic='" + storeEntrancePic + '\'' +
                ", indoorPic='" + indoorPic + '\'' +
                ", merchantShortname='" + merchantShortname + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", rate='" + rate + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", idCardValidTimeBegin='" + idCardValidTimeBegin + '\'' +
                ", idCardValidTimeEnd='" + idCardValidTimeEnd + '\'' +
                '}';
    }
}

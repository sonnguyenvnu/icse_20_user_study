package com.roncoo.pay.notify.entity;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.pay.common.core.entity.BaseEntity;
import com.roncoo.pay.notify.enums.NotifyStatusEnum;

import java.util.Date;
import java.util.Map;

/**
 * @功能说明:   订�?�结果查询实体,主�?用于MQ查询上游订�?�结果时,查询规则�?�查询结果
 * @创建者: Peter
 * @创建时间: 16/6/2  上�?�11:20
 * @公�?��??称:广州市领课网络科技有�?公�?� 龙果学院(www.roncoo.com)
 * @版本:V1.0
 */
public class RpOrderResultQueryVo extends BaseEntity {

    private static final long serialVersionUID = -6104194914044220447L;

    private Date createTime;

    /** 通知规则 */
    private String notifyRule;

    /** 最�?�一次通知时间 **/
    private Date lastNotifyTime;

    /** 通知次数 **/
    private Integer notifyTimes;

    /** �?制通知次数 **/
    private Integer limitNotifyTimes;

    /** 银行订�?��?� **/
    private String bankOrderNo;

    public RpOrderResultQueryVo() {
        super();
    }

    public RpOrderResultQueryVo(Date createTime, String notifyRule, Date lastNotifyTime, Integer notifyTimes, Integer limitNotifyTimes,
                                String bankOrderNo, NotifyStatusEnum status) {
        super();
        this.createTime = createTime;
        this.notifyRule = notifyRule;
        this.lastNotifyTime = lastNotifyTime;
        this.notifyTimes = notifyTimes;
        this.limitNotifyTimes = limitNotifyTimes;
        this.bankOrderNo = bankOrderNo;
        super.setStatus(status.name());
    }



    /** 通知规则 */
    public String getNotifyRule() {
        return notifyRule;
    }

    /** 通知规则 */
    public void setNotifyRule(String notifyRule) {
        this.notifyRule = notifyRule;
    }

    /**
     * 获�?�通知规则的Map<String, Integer>.
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<Integer, Integer> getNotifyRuleMap(){
        return (Map) JSONObject.parseObject(getNotifyRule());
    }

    /** 最�?�一次通知时间 **/
    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    /** 最�?�一次通知时间 **/
    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    /** 通知次数 **/
    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    /** 通知次数 **/
    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** �?制通知次数 **/
    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    /** �?制通知次数 **/
    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }
}

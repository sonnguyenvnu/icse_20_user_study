/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.trade.service.impl;

import com.alibaba.fastjson.JSON;
import com.roncoo.pay.account.service.RpAccountTransactionService;
import com.roncoo.pay.common.core.enums.PayTypeEnum;
import com.roncoo.pay.common.core.enums.PayWayEnum;
import com.roncoo.pay.common.core.enums.PublicEnum;
import com.roncoo.pay.common.core.utils.DateUtils;
import com.roncoo.pay.common.core.utils.StringUtil;
import com.roncoo.pay.notify.service.RpNotifyService;
import com.roncoo.pay.trade.dao.RpTradePaymentOrderDao;
import com.roncoo.pay.trade.dao.RpTradePaymentRecordDao;
import com.roncoo.pay.trade.entity.RoncooPayGoodsDetails;
import com.roncoo.pay.trade.entity.RpTradePaymentOrder;
import com.roncoo.pay.trade.entity.RpTradePaymentRecord;
import com.roncoo.pay.trade.entity.weixinpay.WeiXinPrePay;
import com.roncoo.pay.trade.enums.OrderFromEnum;
import com.roncoo.pay.trade.enums.TradeStatusEnum;
import com.roncoo.pay.trade.enums.TrxTypeEnum;
import com.roncoo.pay.trade.enums.alipay.AliPayTradeStateEnum;
import com.roncoo.pay.trade.enums.weixinpay.WeiXinTradeTypeEnum;
import com.roncoo.pay.trade.enums.weixinpay.WeixinTradeStateEnum;
import com.roncoo.pay.trade.exception.TradeBizException;
import com.roncoo.pay.trade.service.RpTradePaymentManagerService;
import com.roncoo.pay.trade.utils.MerchantApiUtil;
import com.roncoo.pay.trade.utils.WeiXinPayUtils;
import com.roncoo.pay.trade.utils.WeixinConfigUtil;
import com.roncoo.pay.trade.utils.alipay.AliPayUtil;
import com.roncoo.pay.trade.utils.alipay.config.AlipayConfigUtil;
import com.roncoo.pay.trade.utils.alipay.util.AlipayNotify;
import com.roncoo.pay.trade.utils.alipay.util.AlipaySubmit;
import com.roncoo.pay.trade.utils.weixin.WeiXinPayUtil;
import com.roncoo.pay.trade.vo.*;
import com.roncoo.pay.user.entity.RpPayWay;
import com.roncoo.pay.user.entity.RpUserInfo;
import com.roncoo.pay.user.entity.RpUserPayConfig;
import com.roncoo.pay.user.entity.RpUserPayInfo;
import com.roncoo.pay.user.enums.FundInfoTypeEnum;
import com.roncoo.pay.user.exception.UserBizException;
import com.roncoo.pay.user.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * <b>功能说明:交易模�?�管�?�实现类实现</b>
 *
 * @author Peter <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
@Service("rpTradePaymentManagerService")
public class RpTradePaymentManagerServiceImpl implements RpTradePaymentManagerService {

    private static final Logger LOG = LoggerFactory.getLogger(RpTradePaymentManagerServiceImpl.class);

    @Autowired
    private RpTradePaymentOrderDao rpTradePaymentOrderDao;

    @Autowired
    private RpTradePaymentRecordDao rpTradePaymentRecordDao;

    @Autowired
    private RpUserInfoService rpUserInfoService;

    @Autowired
    private RpUserPayInfoService rpUserPayInfoService;

    @Autowired
    private RpUserPayConfigService rpUserPayConfigService;

    @Autowired
    private RpPayWayService rpPayWayService;

    @Autowired
    private BuildNoService buildNoService;

    @Autowired
    private RpNotifyService rpNotifyService;

    @Autowired
    private RpAccountTransactionService rpAccountTransactionService;

    /*@Autowired
    private AliF2FPaySubmit aliF2FPaySubmit;*/

    /**
     * �?始化直连扫�?支付数�?�,直连扫�?支付�?始化方法规则 1:根�?�(商户编�?� + 商户订�?��?�)确定订�?�是�?�存在
     * 1.1:如果订�?�存在,抛异常,�??示订�?�已存在 1.2:如果订�?��?存在,创建支付订�?� 2:创建支付记录 3:根�?�相应渠�?�方法
     * 4:调转到相应支付渠�?�扫�?界�?�
     *
     * @param payKey      商户支付KEY
     * @param productName 产�?�??称
     * @param orderNo     商户订�?��?�
     * @param orderDate   下�?�日期
     * @param orderTime   下�?�时间
     * @param orderPrice  订�?�金�?(元)
     * @param payWayCode  支付方�?编�?
     * @param orderIp     下�?�IP
     * @param orderPeriod 订�?�有效期(分钟)
     * @param returnUrl   支付结果页�?�通知地�?�
     * @param notifyUrl   支付结果�?��?�通知地�?�
     * @param remark      支付备注
     * @param field1      扩展字段1
     * @param field2      扩展字段2
     * @param field3      扩展字段3
     * @param field4      扩展字段4
     * @param field5      扩展字段5
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScanPayResultVo initDirectScanPay(String payKey, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, Integer orderPeriod, String returnUrl, String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5) {

        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        // 根�?�支付产�?�?�支付方�?获�?�费率
        RpPayWay payWay = null;
        PayTypeEnum payType = null;
        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, PayTypeEnum.SCANPAY.name());
            payType = PayTypeEnum.SCANPAY;
        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, PayTypeEnum.DIRECT_PAY.name());
            payType = PayTypeEnum.DIRECT_PAY;
        }

        if (payWay == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        String merchantNo = rpUserPayConfig.getUserNo();// 商户编�?�
        RpUserInfo rpUserInfo = rpUserInfoService.getDataByMerchentNo(merchantNo);
        if (rpUserInfo == null) {
            throw new UserBizException(UserBizException.USER_IS_NULL, "用户�?存在");
        }

        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo, orderNo);
        if (rpTradePaymentOrder == null) {
            rpTradePaymentOrder = sealRpTradePaymentOrder(merchantNo, rpUserInfo.getUserName(), productName, orderNo, orderDate, orderTime, orderPrice, payWayCode, PayWayEnum.getEnum(payWayCode).getDesc(), payType, rpUserPayConfig.getFundIntoType(), orderIp, orderPeriod, returnUrl, notifyUrl, remark, field1, field2, field3, field4, field5);
            rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
        } else {
            if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�已支付�?功,无需�?�?支付");
            }
            if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
                rpTradePaymentOrder.setOrderAmount(orderPrice);// 如果金�?�?一致,修改金�?为最新的金�?
            }
        }

        return getScanPayResultVo(rpTradePaymentOrder, payWay);

    }

    /**
     * �?��?支付,对应的是支付�?的�?��?支付或者微信的刷�?�支付
     *
     * @param payKey      商户支付key
     * @param authCode    支付授�?��?
     * @param productName 产�?�??称
     * @param orderNo     商户订�?��?�
     * @param orderDate   下�?�日期
     * @param orderTime   下�?�时间
     * @param orderPrice  订�?�金�?(元)
     * @param payWayCode  支付方�?
     * @param orderIp     下�?�IP
     * @param remark      支付备注
     * @param field1      扩展字段1
     * @param field2      扩展字段2
     * @param field3      扩展字段3
     * @param field4      扩展字段4
     * @param field5      扩展字段5
     * @return
     */
    @Override
    public F2FPayResultVo f2fPay(String payKey, String authCode, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, String remark, String field1, String field2, String field3, String field4, String field5) {

        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        if (StringUtil.isEmpty(authCode)) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "支付授�?��?�?能为空");
        }
        // 根�?�支付产�?�?�支付方�?获�?�费率
        RpPayWay payWay = null;
        PayTypeEnum payType = null;
        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, PayTypeEnum.MICRO_PAY.name());
            payType = PayTypeEnum.MICRO_PAY;
        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, PayTypeEnum.F2F_PAY.name());
            payType = PayTypeEnum.F2F_PAY;
        }
        if (payWay == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        String merchantNo = rpUserPayConfig.getUserNo();// 商户编�?�
        RpUserInfo rpUserInfo = rpUserInfoService.getDataByMerchentNo(merchantNo);
        if (rpUserInfo == null) {
            throw new UserBizException(UserBizException.USER_IS_NULL, "用户�?存在");
        }

        //根�?�商户�?�和订�?��?�去查询订�?�是�?�存在
        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo, orderNo);
        if (rpTradePaymentOrder == null) {
            //订�?��?存在，创建订�?�
            rpTradePaymentOrder = sealRpTradePaymentOrder(merchantNo, rpUserInfo.getUserName(), productName, orderNo, orderDate, orderTime, orderPrice, payWayCode, PayWayEnum.getEnum(payWayCode).getDesc(), payType, rpUserPayConfig.getFundIntoType(), orderIp, 5, "f2fPay", "f2fPay", remark, field1, field2, field3, field4, field5);
            rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
        } else {
            //订�?�已存在，订�?�金�?与传入金�?�?相等
            if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "错误的订�?�");
            }
            //订�?�已存在，且订�?�状�?为支付�?功
            if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�已支付�?功,无需�?�?支付");
            }
        }

        return getF2FPayResultVo(rpTradePaymentOrder, payWay, payKey, rpUserPayConfig.getPaySecret(), authCode, null);
    }

    /**
     * 通过支付订�?��?�商户费率生�?支付记录
     *
     * @param rpTradePaymentOrder 支付订�?�
     * @param payWay              商户支付�?置
     * @return
     */
    private F2FPayResultVo getF2FPayResultVo(RpTradePaymentOrder rpTradePaymentOrder, RpPayWay payWay, String payKey, String merchantPaySecret, String authCode, List<RoncooPayGoodsDetails> roncooPayGoodsDetailses) {

        F2FPayResultVo f2FPayResultVo = new F2FPayResultVo();
        String payWayCode = payWay.getPayWayCode();// 支付方�?

        PayTypeEnum payType = null;
        if (PayWayEnum.WEIXIN.name().equals(payWay.getPayWayCode())) {
            payType = PayTypeEnum.MICRO_PAY;
        } else if (PayWayEnum.ALIPAY.name().equals(payWay.getPayWayCode())) {
            payType = PayTypeEnum.F2F_PAY;
        }

        rpTradePaymentOrder.setPayTypeCode(payType.name());// 支付类型
        rpTradePaymentOrder.setPayTypeName(payType.getDesc());// 支付方�?
        rpTradePaymentOrder.setPayWayCode(payWay.getPayWayCode());//支付通�?�编�?�
        rpTradePaymentOrder.setPayWayName(payWay.getPayWayName());//支付通�?��??称

        //生�?支付�?水
        RpTradePaymentRecord rpTradePaymentRecord = sealRpTradePaymentRecord(rpTradePaymentOrder.getMerchantNo(), rpTradePaymentOrder.getMerchantName(), rpTradePaymentOrder.getProductName(), rpTradePaymentOrder.getMerchantOrderNo(), rpTradePaymentOrder.getOrderAmount(), payWay.getPayWayCode(), payWay.getPayWayName(), payType, rpTradePaymentOrder.getFundIntoType(), BigDecimal.valueOf(payWay.getPayRate()), rpTradePaymentOrder.getOrderIp(), rpTradePaymentOrder.getReturnUrl(), rpTradePaymentOrder.getNotifyUrl(), rpTradePaymentOrder.getRemark(), rpTradePaymentOrder.getField1(), rpTradePaymentOrder.getField2(), rpTradePaymentOrder.getField3(), rpTradePaymentOrder.getField4(), rpTradePaymentOrder.getField5());
        rpTradePaymentRecordDao.insert(rpTradePaymentRecord);

        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {// 微信支付
            RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(rpTradePaymentOrder.getMerchantNo(), payWayCode);
            if (rpUserPayInfo == null) {
                throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "商户支付�?置有误");
            }
            Map<String, Object> wxResultMap = WeiXinPayUtil.micropay(rpTradePaymentRecord.getBankOrderNo(), rpTradePaymentOrder.getProductName(), rpTradePaymentRecord.getOrderAmount(), rpTradePaymentRecord.getOrderIp(), authCode);
            if (wxResultMap == null || wxResultMap.isEmpty()) {
                //返回结果为空，支付结果未知需�?轮询
                rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
            } else {
                if ("YES".equals(wxResultMap.get("verify"))) {
                    //验签�?功
                    if ("SUCCESS".equals(wxResultMap.get("return_code")) && "SUCCESS".equals(wxResultMap.get("result_code"))) {
                        //通讯�?功且业务结果为�?功
                        completeSuccessOrder(rpTradePaymentRecord, String.valueOf(wxResultMap.get("transaction_id")), new Date(), "支付�?功");
                    } else if ("SUCCESS".equals(wxResultMap.get("return_code")) && !StringUtil.isEmpty(wxResultMap.get("err_code")) && !"BANKERROR".equals(wxResultMap.get("err_code")) && !"USERPAYING".equals(wxResultMap.get("err_code")) && !"SYSTEMERROR".equals(wxResultMap.get("err_code"))) {
                        //支付失败
                        completeFailOrder(rpTradePaymentRecord, String.valueOf(wxResultMap.get("err_code_des")));
                    } else {
                        //返回结果未知，需�?轮询
                        rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
                    }
                } else {
                    completeFailOrder(rpTradePaymentRecord, "签�??校验失败!");
                    //验签失败
                }
            }

        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {// 支付�?支付
            RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(rpTradePaymentOrder.getMerchantNo(), payWayCode);
            if (rpUserPayInfo == null) {
                throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "商户支付�?置有误");
            }
            Map<String, Object> resultMap = AliPayUtil.tradePay(rpTradePaymentRecord.getBankOrderNo(), authCode, rpTradePaymentOrder.getProductName(), rpTradePaymentRecord.getOrderAmount(), "", roncooPayGoodsDetailses);
            //支付�?��?支付--统一根�?�订�?�轮询去确认支付结果
            rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
        } else {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "错误的支付方�?");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        f2FPayResultVo.setStatus(rpTradePaymentRecord.getStatus());// 支付结果
        paramMap.put("status", rpTradePaymentRecord.getStatus());

        f2FPayResultVo.setField1(rpTradePaymentRecord.getField1());// 扩展字段1
        paramMap.put("field1", rpTradePaymentRecord.getField1());

        f2FPayResultVo.setField2(rpTradePaymentRecord.getField2());// 扩展字段2
        paramMap.put("field2", rpTradePaymentRecord.getField2());

        f2FPayResultVo.setField3(rpTradePaymentRecord.getField3());// 扩展字段3
        paramMap.put("field3", rpTradePaymentRecord.getField3());

        f2FPayResultVo.setField4(rpTradePaymentRecord.getField4());// 扩展字段4
        paramMap.put("field4", rpTradePaymentRecord.getField4());

        f2FPayResultVo.setField5(rpTradePaymentRecord.getField5());// 扩展字段5
        paramMap.put("field5", rpTradePaymentRecord.getField5());

        f2FPayResultVo.setOrderIp(rpTradePaymentRecord.getOrderIp());// 下�?�ip
        paramMap.put("orderIp", rpTradePaymentRecord.getOrderIp());

        f2FPayResultVo.setOrderNo(rpTradePaymentRecord.getMerchantOrderNo());// 商户订�?��?�
        paramMap.put("merchantOrderNo", rpTradePaymentRecord.getMerchantOrderNo());

        f2FPayResultVo.setPayKey(payKey);// 支付�?�
        paramMap.put("payKey", payKey);

        f2FPayResultVo.setProductName(rpTradePaymentRecord.getProductName());// 产�?�??称
        paramMap.put("productName", rpTradePaymentRecord.getProductName());

        f2FPayResultVo.setRemark(rpTradePaymentRecord.getRemark());// 支付备注
        paramMap.put("remark", rpTradePaymentRecord.getRemark());

        f2FPayResultVo.setTrxNo(rpTradePaymentRecord.getTrxNo());// 交易�?水�?�
        paramMap.put("trxNo", rpTradePaymentRecord.getTrxNo());

        String sign = MerchantApiUtil.getSign(paramMap, merchantPaySecret);

        f2FPayResultVo.setSign(sign);
        return f2FPayResultVo;
    }

    /**
     * 支付�?功方法
     *
     * @param rpTradePaymentRecord
     */
    @Transactional(rollbackFor = Exception.class)
    void completeSuccessOrder(RpTradePaymentRecord rpTradePaymentRecord, String bankTrxNo, Date timeEnd, String bankReturnMsg) {
        LOG.info("订�?�支付�?功!");
        rpTradePaymentRecord.setPaySuccessTime(timeEnd);
        rpTradePaymentRecord.setBankTrxNo(bankTrxNo);// 设置银行�?水�?�
        rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
        rpTradePaymentRecord.setStatus(TradeStatusEnum.SUCCESS.name());
        rpTradePaymentRecordDao.update(rpTradePaymentRecord);

        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getMerchantOrderNo());
        rpTradePaymentOrder.setStatus(TradeStatusEnum.SUCCESS.name());
        rpTradePaymentOrder.setTrxNo(rpTradePaymentRecord.getTrxNo());// 设置支付平�?�支付�?水�?�
        rpTradePaymentOrderDao.update(rpTradePaymentOrder);

        if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(rpTradePaymentRecord.getFundIntoType())) {
            rpAccountTransactionService.creditToAccount(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getOrderAmount().subtract(rpTradePaymentRecord.getPlatIncome()), rpTradePaymentRecord.getBankOrderNo(), rpTradePaymentRecord.getBankTrxNo(), rpTradePaymentRecord.getTrxType(), rpTradePaymentRecord.getRemark());
        }

        if (PayTypeEnum.F2F_PAY.name().equals(rpTradePaymentOrder.getPayTypeCode())) {// 支付�?
            // �?��?支付实时返回支付结果,�?需�?商户通知（修改�?�，�?��?支付结果通过订�?�轮询去确认订�?�状�?，�?功�?�通知商户）
            String notifyUrl = getMerchantNotifyUrl(rpTradePaymentRecord, rpTradePaymentOrder, rpTradePaymentRecord.getNotifyUrl(), TradeStatusEnum.SUCCESS);
            rpNotifyService.notifySend(notifyUrl, rpTradePaymentRecord.getMerchantOrderNo(), rpTradePaymentRecord.getMerchantNo());
            //return;
        } else {
            String notifyUrl = getMerchantNotifyUrl(rpTradePaymentRecord, rpTradePaymentOrder, rpTradePaymentRecord.getNotifyUrl(), TradeStatusEnum.SUCCESS);
            rpNotifyService.notifySend(notifyUrl, rpTradePaymentRecord.getMerchantOrderNo(), rpTradePaymentRecord.getMerchantNo());
        }
    }

    private String getMerchantNotifyUrl(RpTradePaymentRecord rpTradePaymentRecord, RpTradePaymentOrder rpTradePaymentOrder, String sourceUrl, TradeStatusEnum tradeStatusEnum) {

        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByUserNo(rpTradePaymentRecord.getMerchantNo());
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        Map<String, Object> paramMap = new HashMap<>();

        String payKey = rpUserPayConfig.getPayKey();// �?业支付KEY
        paramMap.put("payKey", payKey);
        String productName = rpTradePaymentRecord.getProductName(); // 商�?�??称
        paramMap.put("productName", productName);
        String orderNo = rpTradePaymentRecord.getMerchantOrderNo(); // 订�?�编�?�
        paramMap.put("orderNo", orderNo);
        BigDecimal orderPrice = rpTradePaymentRecord.getOrderAmount(); // 订�?�金�? ,
        // �?��?:元
        paramMap.put("orderPrice", orderPrice);
        String payWayCode = rpTradePaymentRecord.getPayWayCode(); // 支付方�?编�? 支付�?:
        // ALIPAY
        // 微信:WEIXIN
        paramMap.put("payWayCode", payWayCode);
        paramMap.put("tradeStatus", tradeStatusEnum);// 交易状�?
        String orderDateStr = DateUtils.formatDate(rpTradePaymentOrder.getOrderDate(), "yyyyMMdd"); // 订�?�日期
        paramMap.put("orderDate", orderDateStr);
        String orderTimeStr = DateUtils.formatDate(rpTradePaymentOrder.getOrderTime(), "yyyyMMddHHmmss"); // 订�?�时间
        paramMap.put("orderTime", orderTimeStr);
        String remark = rpTradePaymentRecord.getRemark(); // 支付备注
        paramMap.put("remark", remark);
        String trxNo = rpTradePaymentRecord.getTrxNo();// 支付�?水�?�
        paramMap.put("trxNo", trxNo);

        String field1 = rpTradePaymentOrder.getField1(); // 扩展字段1
        paramMap.put("field1", field1);
        String field2 = rpTradePaymentOrder.getField2(); // 扩展字段2
        paramMap.put("field2", field2);
        String field3 = rpTradePaymentOrder.getField3(); // 扩展字段3
        paramMap.put("field3", field3);
        String field4 = rpTradePaymentOrder.getField4(); // 扩展字段4
        paramMap.put("field4", field4);
        String field5 = rpTradePaymentOrder.getField5(); // 扩展字段5
        paramMap.put("field5", field5);

        String paramStr = MerchantApiUtil.getParamStr(paramMap);
        String sign = MerchantApiUtil.getSign(paramMap, rpUserPayConfig.getPaySecret());
        String notifyUrl = sourceUrl + "?" + paramStr + "&sign=" + sign;

        return notifyUrl;
    }

    /**
     * 支付失败方法
     *
     * @param rpTradePaymentRecord
     */
    private void completeFailOrder(RpTradePaymentRecord rpTradePaymentRecord, String bankReturnMsg) {
        rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
        rpTradePaymentRecord.setStatus(TradeStatusEnum.FAILED.name());
        rpTradePaymentRecordDao.update(rpTradePaymentRecord);

        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getMerchantOrderNo());
        rpTradePaymentOrder.setStatus(TradeStatusEnum.FAILED.name());
        rpTradePaymentOrderDao.update(rpTradePaymentOrder);

        String notifyUrl = getMerchantNotifyUrl(rpTradePaymentRecord, rpTradePaymentOrder, rpTradePaymentRecord.getNotifyUrl(), TradeStatusEnum.FAILED);
        rpNotifyService.notifySend(notifyUrl, rpTradePaymentRecord.getMerchantOrderNo(), rpTradePaymentRecord.getMerchantNo());
    }

    /**
     * �?始化�?�直连扫�?支付数�?�,�?�直连扫�?支付�?始化方法规则 1:根�?�(商户编�?� + 商户订�?��?�)确定订�?�是�?�存在
     * 1.1:如果订�?�存在且为未支付,抛异常�??示订�?�已存在 1.2:如果订�?��?存在,创建支付订�?� 2:获�?�商户支付�?置,跳转到支付网关,选择支付方�?
     *
     * @param payKey      商户支付KEY
     * @param productName 产�?�??称
     * @param orderNo     商户订�?��?�
     * @param orderDate   下�?�日期
     * @param orderTime   下�?�时间
     * @param orderPrice  订�?�金�?(元)
     * @param orderIp     下�?�IP
     * @param orderPeriod 订�?�有效期(分钟)
     * @param returnUrl   支付结果页�?�通知地�?�
     * @param notifyUrl   支付结果�?��?�通知地�?�
     * @param remark      支付备注
     * @param field1      扩展字段1
     * @param field2      扩展字段2
     * @param field3      扩展字段3
     * @param field4      扩展字段4
     * @param field5      扩展字段5
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RpPayGateWayPageShowVo initNonDirectScanPay(String payKey, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String orderIp, Integer orderPeriod, String returnUrl, String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5) {

        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        String merchantNo = rpUserPayConfig.getUserNo();// 商户编�?�
        RpUserInfo rpUserInfo = rpUserInfoService.getDataByMerchentNo(merchantNo);
        if (rpUserInfo == null) {
            throw new UserBizException(UserBizException.USER_IS_NULL, "用户�?存在");
        }

        List<RpPayWay> payWayList = rpPayWayService.listByProductCode(rpUserPayConfig.getProductCode());
        if (payWayList == null || payWayList.size() <= 0) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "支付产�?�?置有误");
        }

        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo, orderNo);
        if (rpTradePaymentOrder == null) {
            rpTradePaymentOrder = sealRpTradePaymentOrder(merchantNo, rpUserInfo.getUserName(), productName, orderNo, orderDate, orderTime, orderPrice, null, null, null, rpUserPayConfig.getFundIntoType(), orderIp, orderPeriod, returnUrl, notifyUrl, remark, field1, field2, field3, field4, field5);
            rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
        } else {

            if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�已支付�?功,无需�?�?支付");
            }

            if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
                rpTradePaymentOrder.setOrderAmount(orderPrice);// 如果金�?�?一致,修改金�?为最新的金�?
                rpTradePaymentOrderDao.update(rpTradePaymentOrder);
            }
        }

        RpPayGateWayPageShowVo payGateWayPageShowVo = new RpPayGateWayPageShowVo();
        payGateWayPageShowVo.setProductName(rpTradePaymentOrder.getProductName());// 产�?�??称
        payGateWayPageShowVo.setMerchantName(rpTradePaymentOrder.getMerchantName());// 商户�??称
        payGateWayPageShowVo.setOrderAmount(rpTradePaymentOrder.getOrderAmount());// 订�?�金�?
        payGateWayPageShowVo.setMerchantOrderNo(rpTradePaymentOrder.getMerchantOrderNo());// 商户订�?��?�
        payGateWayPageShowVo.setPayKey(payKey);// 商户支付key

        Map<String, PayWayEnum> payWayEnumMap = new HashMap<String, PayWayEnum>();
        for (RpPayWay payWay : payWayList) {
            payWayEnumMap.put(payWay.getPayWayCode(), PayWayEnum.getEnum(payWay.getPayWayCode()));
        }

        payGateWayPageShowVo.setPayWayEnumMap(payWayEnumMap);

        return payGateWayPageShowVo;

    }

    /**
     * �?�直连扫�?支付,选择支付方�?�?�,去支付
     *
     * @param payKey
     * @param orderNo
     * @param payWayCode
     * @return
     */
    @Override
    public ScanPayResultVo toNonDirectScanPay(String payKey, String orderNo, String payWayCode) {

        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        // 根�?�支付产�?�?�支付方�?获�?�费率
        RpPayWay payWay = null;
        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, PayTypeEnum.SCANPAY.name());
        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, PayTypeEnum.DIRECT_PAY.name());
        }

        if (payWay == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        String merchantNo = rpUserPayConfig.getUserNo();// 商户编�?�
        RpUserInfo rpUserInfo = rpUserInfoService.getDataByMerchentNo(merchantNo);
        if (rpUserInfo == null) {
            throw new UserBizException(UserBizException.USER_IS_NULL, "用户�?存在");
        }

        // 根�?�商户订�?��?�获�?�订�?�信�?�
        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo, orderNo);
        if (rpTradePaymentOrder == null) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?��?存在");
        }

        if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�已支付�?功,无需�?�?支付");
        }

        return getScanPayResultVo(rpTradePaymentOrder, payWay);

    }

    /**
     * 通过支付订�?��?�商户费率生�?支付记录
     *
     * @param rpTradePaymentOrder 支付订�?�
     * @param payWay              商户支付�?置
     * @return
     */
    private ScanPayResultVo getScanPayResultVo(RpTradePaymentOrder rpTradePaymentOrder, RpPayWay payWay) {

        ScanPayResultVo scanPayResultVo = new ScanPayResultVo();
        String payWayCode = payWay.getPayWayCode();// 支付方�?

        PayTypeEnum payType = null;
        if (PayWayEnum.WEIXIN.name().equals(payWay.getPayWayCode())) {
            payType = PayTypeEnum.SCANPAY;
        } else if (PayWayEnum.ALIPAY.name().equals(payWay.getPayWayCode())) {
            payType = PayTypeEnum.DIRECT_PAY;
        }

        rpTradePaymentOrder.setPayTypeCode(payType.name());
        rpTradePaymentOrder.setPayTypeName(payType.getDesc());
        rpTradePaymentOrder.setPayWayCode(payWay.getPayWayCode());
        rpTradePaymentOrder.setPayWayName(payWay.getPayWayName());
        rpTradePaymentOrderDao.update(rpTradePaymentOrder);

        RpTradePaymentRecord rpTradePaymentRecord = sealRpTradePaymentRecord(rpTradePaymentOrder.getMerchantNo(), rpTradePaymentOrder.getMerchantName(), rpTradePaymentOrder.getProductName(), rpTradePaymentOrder.getMerchantOrderNo(), rpTradePaymentOrder.getOrderAmount(), payWay.getPayWayCode(), payWay.getPayWayName(), payType, rpTradePaymentOrder.getFundIntoType(), BigDecimal.valueOf(payWay.getPayRate()), rpTradePaymentOrder.getOrderIp(), rpTradePaymentOrder.getReturnUrl(), rpTradePaymentOrder.getNotifyUrl(), rpTradePaymentOrder.getRemark(), rpTradePaymentOrder.getField1(), rpTradePaymentOrder.getField2(), rpTradePaymentOrder.getField3(), rpTradePaymentOrder.getField4(), rpTradePaymentOrder.getField5());
        rpTradePaymentRecordDao.insert(rpTradePaymentRecord);

        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {// 微信支付
            String appid = "";
            String mch_id = "";
            String partnerKey = "";
            if (FundInfoTypeEnum.MERCHANT_RECEIVES.name().equals(rpTradePaymentOrder.getFundIntoType())) {// 商户收款
                // 根�?�资金�?�?�获�?��?置信�?�
                RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(rpTradePaymentOrder.getMerchantNo(), payWayCode);
                appid = rpUserPayInfo.getAppId();
                mch_id = rpUserPayInfo.getMerchantId();
                partnerKey = rpUserPayInfo.getPartnerKey();
            } else if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(rpTradePaymentOrder.getFundIntoType())) {// 平�?�收款
                appid = WeixinConfigUtil.readConfig("appId");
                mch_id = WeixinConfigUtil.readConfig("mch_id");
                partnerKey = WeixinConfigUtil.readConfig("partnerKey");
            }

            WeiXinPrePay weiXinPrePay = sealWeixinPerPay(appid, mch_id, rpTradePaymentOrder.getProductName(), rpTradePaymentOrder.getRemark(), rpTradePaymentRecord.getBankOrderNo(), rpTradePaymentOrder.getOrderAmount(), rpTradePaymentOrder.getOrderTime(), rpTradePaymentOrder.getOrderPeriod(), WeiXinTradeTypeEnum.NATIVE, rpTradePaymentRecord.getBankOrderNo(), "", rpTradePaymentOrder.getOrderIp());
            String prePayXml = WeiXinPayUtils.getPrePayXml(weiXinPrePay, partnerKey);
            LOG.info("扫�?支付，微信请求报文:{}", prePayXml);
            // 调用微信支付的功能,获�?�微信支付code_url
            Map<String, Object> prePayRequest = WeiXinPayUtils.httpXmlRequest(WeixinConfigUtil.readConfig("prepay_url"), "POST", prePayXml);
            LOG.info("扫�?支付，微信返回报文:{}", prePayRequest.toString());
            if (WeixinTradeStateEnum.SUCCESS.name().equals(prePayRequest.get("return_code")) && WeixinTradeStateEnum.SUCCESS.name().equals(prePayRequest.get("result_code"))) {
                String weiXinPrePaySign = WeiXinPayUtils.geWeiXintPrePaySign(appid, mch_id, weiXinPrePay.getDeviceInfo(), WeiXinTradeTypeEnum.NATIVE.name(), prePayRequest, partnerKey);
                String codeUrl = String.valueOf(prePayRequest.get("code_url"));
                LOG.info("预支付生�?�?功,{}", codeUrl);
                if (prePayRequest.get("sign").equals(weiXinPrePaySign)) {
                    rpTradePaymentRecord.setBankReturnMsg(prePayRequest.toString());
                    rpTradePaymentRecordDao.update(rpTradePaymentRecord);
                    scanPayResultVo.setCodeUrl(codeUrl);// 设置微信跳转地�?�
                    scanPayResultVo.setPayWayCode(PayWayEnum.WEIXIN.name());
                    scanPayResultVo.setProductName(rpTradePaymentOrder.getProductName());
                    scanPayResultVo.setOrderAmount(rpTradePaymentOrder.getOrderAmount());
                } else {
                    throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR, "微信返回结果签�??异常");
                }
            } else {
                throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR, "请求微信异常");
            }
        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {// 支付�?支付

            // 把请求�?�数打包�?数组
            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("service", AlipayConfigUtil.service);
            sParaTemp.put("partner", AlipayConfigUtil.partner);
            sParaTemp.put("seller_id", AlipayConfigUtil.seller_id);
            sParaTemp.put("_input_charset", AlipayConfigUtil.input_charset);
            sParaTemp.put("payment_type", AlipayConfigUtil.payment_type);
            sParaTemp.put("notify_url", AlipayConfigUtil.notify_url);
            sParaTemp.put("return_url", AlipayConfigUtil.return_url);
            sParaTemp.put("anti_phishing_key", AlipayConfigUtil.anti_phishing_key);
            sParaTemp.put("exter_invoke_ip", AlipayConfigUtil.exter_invoke_ip);
            sParaTemp.put("out_trade_no", rpTradePaymentRecord.getBankOrderNo());
            sParaTemp.put("subject", rpTradePaymentOrder.getProductName());
            sParaTemp.put("total_fee", String.valueOf(rpTradePaymentOrder.getOrderAmount().setScale(2, BigDecimal.ROUND_HALF_UP)));// �?数点�?�两�?
            sParaTemp.put("body", "");
            LOG.info("扫�?支付，支付�?请求�?�数:{}", sParaTemp);

            // 获�?�请求页�?�数�?�
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
            LOG.info("扫�?支付，支付�?返回报文:{}", sHtmlText);

            rpTradePaymentRecord.setBankReturnMsg(sHtmlText);
            rpTradePaymentRecordDao.update(rpTradePaymentRecord);
            scanPayResultVo.setCodeUrl(sHtmlText);// 设置支付�?跳转地�?�
            scanPayResultVo.setPayWayCode(PayWayEnum.ALIPAY.name());
            scanPayResultVo.setProductName(rpTradePaymentOrder.getProductName());
            scanPayResultVo.setOrderAmount(rpTradePaymentOrder.getOrderAmount());

        } else {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "错误的支付方�?");
        }
        rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
        return scanPayResultVo;
    }

    /**
     * 完�?扫�?支付(支付�?�?�时到账支付)
     *
     * @param payWayCode
     * @param notifyMap
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String completeScanPay(String payWayCode, Map<String, String> notifyMap) {
        LOG.info("接收到{}支付结果{}", payWayCode, notifyMap);

        String returnStr = null;
        String bankOrderNo = notifyMap.get("out_trade_no");
        // 根�?�银行订�?��?�获�?�支付信�?�
        RpTradePaymentRecord rpTradePaymentRecord = rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
        if (rpTradePaymentRecord == null) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, ",�?�法订�?�,订�?��?存在");
        }

        if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentRecord.getStatus())) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�为�?功状�?");
        }
        String merchantNo = rpTradePaymentRecord.getMerchantNo();// 商户编�?�

        // 根�?�支付订�?�获�?��?置信�?�
        String fundIntoType = rpTradePaymentRecord.getFundIntoType();// 获�?�资金�?入类型
        String partnerKey = "";

        if (FundInfoTypeEnum.MERCHANT_RECEIVES.name().equals(fundIntoType)) {// 商户收款
            // 根�?�资金�?�?�获�?��?置信�?�
            RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(merchantNo, PayWayEnum.WEIXIN.name());
            partnerKey = rpUserPayInfo.getPartnerKey();

        } else if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(fundIntoType)) {// 平�?�收款
            partnerKey = WeixinConfigUtil.readConfig("partnerKey");

            RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByUserNo(merchantNo);
            if (rpUserPayConfig == null) {
                throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
            }
            // 根�?�支付产�?�?�支付方�?获�?�费率
            RpPayWay payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), rpTradePaymentRecord.getPayWayCode(), rpTradePaymentRecord.getPayTypeCode());
            if (payWay == null) {
                throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
            }
        }

        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
            String sign = notifyMap.remove("sign");
            if (WeiXinPayUtils.notifySign(notifyMap, sign, partnerKey)) {// 根�?��?置信�?�验�?签�??
                if (WeixinTradeStateEnum.SUCCESS.name().equals(notifyMap.get("result_code"))) {// 业务结果
                    // �?功
                    String timeEndStr = notifyMap.get("time_end");
                    Date timeEnd = null;
                    if (!StringUtil.isEmpty(timeEndStr)) {
                        timeEnd = DateUtils.getDateFromString(timeEndStr, "yyyyMMddHHmmss");// 订�?�支付完�?时间
                    }
                    completeSuccessOrder(rpTradePaymentRecord, notifyMap.get("transaction_id"), timeEnd, notifyMap.toString());
                    returnStr = "<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n" + "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
                } else {
                    completeFailOrder(rpTradePaymentRecord, notifyMap.toString());
                }
            } else {
                throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR, "微信签�??失败");
            }

        } else if ("WEIXIN_PROGRAM".equals(payWayCode)) {

            String sign = notifyMap.remove("sign");
            if (WeiXinPayUtils.notifySign(notifyMap, sign, WeixinConfigUtil.xPayKey)) {// 根�?��?置信�?�验�?签�??
                if (WeixinTradeStateEnum.SUCCESS.name().equals(notifyMap.get("result_code"))) {// 业务结果
                    // �?功
                    String timeEndStr = notifyMap.get("time_end");
                    Date timeEnd = null;
                    if (!StringUtil.isEmpty(timeEndStr)) {
                        timeEnd = DateUtils.getDateFromString(timeEndStr, "yyyyMMddHHmmss");// 订�?�支付完�?时间
                    }
                    completeSuccessOrder(rpTradePaymentRecord, notifyMap.get("transaction_id"), timeEnd, notifyMap.toString());
                    returnStr = "<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n" + "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
                } else {
                    completeFailOrder(rpTradePaymentRecord, notifyMap.toString());
                }
            } else {
                throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR, "微信签�??失败");
            }


        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
            if (AlipayNotify.verify(notifyMap)) {// 验�?�?功
                String tradeStatus = notifyMap.get("trade_status");
                if (AliPayTradeStateEnum.TRADE_FINISHED.name().equals(tradeStatus)) {
                    // 判断该笔订�?�是�?�在商户网站中已�?�?�过处�?�
                    // 如果没有�?�过处�?�，根�?�订�?��?�（out_trade_no）在商户网站的订�?�系统中查到该笔订�?�的详细，并执行商户的业务程�?
                    // 请务必判断请求时的total_fee�?seller_id与通知时获�?�的total_fee�?seller_id为一致的
                    // 如果有�?�过处�?�，�?执行商户的业务程�?

                    // 注�?：
                    // 退款日期超过�?�退款期�?�?�（如三个月�?�退款），支付�?系统�?��?该交易状�?通知
                } else if (AliPayTradeStateEnum.TRADE_SUCCESS.name().equals(tradeStatus)) {

                    String gmtPaymentStr = notifyMap.get("gmt_payment");// 付款时间
                    Date timeEnd = null;
                    if (!StringUtil.isEmpty(gmtPaymentStr)) {
                        timeEnd = DateUtils.getDateFromString(gmtPaymentStr, "yyyy-MM-dd HH:mm:ss");
                    }
                    completeSuccessOrder(rpTradePaymentRecord, notifyMap.get("trade_no"), timeEnd, notifyMap.toString());
                    returnStr = "success";
                } else {
                    completeFailOrder(rpTradePaymentRecord, notifyMap.toString());
                    returnStr = "fail";
                }
            } else {// 验�?失败
                throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR, "支付�?签�??异常");
            }
        } else {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "错误的支付方�?");
        }

        LOG.info("返回支付通�?�{}信�?�{}", payWayCode, returnStr);
        return returnStr;
    }

    /**
     * 支付�?功�?�,�?�是会出现页�?�通知早与�?��?�通知 现页�?�通知,暂时�?�?�数�?�处�?�功能,�?�生�?页�?�通知URL
     *
     * @param payWayCode
     * @param resultMap
     * @return
     */
    @Override
    public OrderPayResultVo completeScanPayByResult(String payWayCode, Map<String, String> resultMap) {

        OrderPayResultVo orderPayResultVo = new OrderPayResultVo();

        String bankOrderNo = resultMap.get("out_trade_no");
        // 根�?�银行订�?��?�获�?�支付信�?�
        RpTradePaymentRecord rpTradePaymentRecord = rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
        if (rpTradePaymentRecord == null) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, ",�?�法订�?�,订�?��?存在");
        }

        orderPayResultVo.setOrderPrice(rpTradePaymentRecord.getOrderAmount());// 订�?�金�?
        orderPayResultVo.setProductName(rpTradePaymentRecord.getProductName());// 产�?�??称

        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getMerchantOrderNo());

        String trade_status = resultMap.get("trade_status");
        // 计算得出通知验�?结果
        boolean verify_result = AlipayNotify.verify(resultMap);
        if (verify_result) {// 验�?�?功
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                String resultUrl = getMerchantNotifyUrl(rpTradePaymentRecord, rpTradePaymentOrder, rpTradePaymentRecord.getReturnUrl(), TradeStatusEnum.SUCCESS);
                orderPayResultVo.setReturnUrl(resultUrl);
                orderPayResultVo.setStatus(TradeStatusEnum.SUCCESS.name());
            } else {
                String resultUrl = getMerchantNotifyUrl(rpTradePaymentRecord, rpTradePaymentOrder, rpTradePaymentRecord.getReturnUrl(), TradeStatusEnum.FAILED);
                orderPayResultVo.setReturnUrl(resultUrl);
                orderPayResultVo.setStatus(TradeStatusEnum.FAILED.name());
            }
        } else {
            throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR, "支付�?签�??异常");
        }
        return orderPayResultVo;
    }

    /**
     * 支付订�?�实体�?装
     *
     * @param merchantNo   商户编�?�
     * @param merchantName 商户�??称
     * @param productName  产�?�??称
     * @param orderNo      商户订�?��?�
     * @param orderDate    下�?�日期
     * @param orderTime    下�?�时间
     * @param orderPrice   订�?�金�?
     * @param payWay       支付方�?
     * @param payWayName   支付方�?�??称
     * @param payType      支付类型
     * @param fundIntoType 资金�?入类型
     * @param orderIp      下�?�IP
     * @param orderPeriod  订�?�有效期
     * @param returnUrl    页�?�通知地�?�
     * @param notifyUrl    �?��?�通知地�?�
     * @param remark       支付备注
     * @param field1       扩展字段1
     * @param field2       扩展字段2
     * @param field3       扩展字段3
     * @param field4       扩展字段4
     * @param field5       扩展字段5
     * @return
     */
    private RpTradePaymentOrder sealRpTradePaymentOrder(String merchantNo, String merchantName, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWay, String payWayName, PayTypeEnum payType, String fundIntoType, String orderIp, Integer orderPeriod, String returnUrl, String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5) {

        RpTradePaymentOrder rpTradePaymentOrder = new RpTradePaymentOrder();
        rpTradePaymentOrder.setProductName(productName);// 商�?�??称
        if (StringUtil.isEmpty(orderNo)) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "订�?��?�错误");
        }

        rpTradePaymentOrder.setMerchantOrderNo(orderNo);// 订�?��?�

        if (orderPrice == null || orderPrice.doubleValue() <= 0) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "订�?�金�?错误");
        }

        rpTradePaymentOrder.setOrderAmount(orderPrice);// 订�?�金�?

        if (StringUtil.isEmpty(merchantName)) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "商户�??称错误");
        }
        rpTradePaymentOrder.setMerchantName(merchantName);// 商户�??称

        if (StringUtil.isEmpty(merchantNo)) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "商户编�?�错误");
        }
        rpTradePaymentOrder.setMerchantNo(merchantNo);// 商户编�?�

        if (orderDate == null) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "下�?�日期错误");
        }
        rpTradePaymentOrder.setOrderDate(orderDate);// 下�?�日期

        if (orderTime == null) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "下�?�时间错误");
        }
        rpTradePaymentOrder.setOrderTime(orderTime);// 下�?�时间
        rpTradePaymentOrder.setOrderIp(orderIp);// 下�?�IP
        rpTradePaymentOrder.setOrderRefererUrl("");// 下�?��?页�?�

        if (StringUtil.isEmpty(returnUrl)) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "页�?�通知地�?�错误");
        }
        rpTradePaymentOrder.setReturnUrl(returnUrl);// 页�?�通知地�?�

        if (StringUtil.isEmpty(notifyUrl)) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "�?��?�通知地�?�错误");
        }
        rpTradePaymentOrder.setNotifyUrl(notifyUrl);// �?��?�通知地�?�

        if (orderPeriod == null || orderPeriod <= 0) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "订�?�有效期错误");
        }
        rpTradePaymentOrder.setOrderPeriod(orderPeriod);// 订�?�有效期

        Date expireTime = DateUtils.addMinute(orderTime, orderPeriod);// 订�?�过期时间
        rpTradePaymentOrder.setExpireTime(expireTime);// 订�?�过期时间
        rpTradePaymentOrder.setPayWayCode(payWay);// 支付通�?�编�?
        rpTradePaymentOrder.setPayWayName(payWayName);// 支付通�?��??称
        rpTradePaymentOrder.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());// 订�?�状�?
        // 等待支付

        if (payType != null) {
            rpTradePaymentOrder.setPayTypeCode(payType.name());// 支付类型
            rpTradePaymentOrder.setPayTypeName(payType.getDesc());// 支付方�?
        }
        rpTradePaymentOrder.setFundIntoType(fundIntoType);// 资金�?入方�?�

        rpTradePaymentOrder.setRemark(remark);// 支付备注
        rpTradePaymentOrder.setField1(field1);// 扩展字段1
        rpTradePaymentOrder.setField2(field2);// 扩展字段2
        rpTradePaymentOrder.setField3(field3);// 扩展字段3
        rpTradePaymentOrder.setField4(field4);// 扩展字段4
        rpTradePaymentOrder.setField5(field5);// 扩展字段5

        return rpTradePaymentOrder;
    }

    /**
     * �?装支付�?水记录实体
     *
     * @param merchantNo   商户编�?�
     * @param merchantName 商户�??称
     * @param productName  产�?�??称
     * @param orderNo      商户订�?��?�
     * @param orderPrice   订�?�金�?
     * @param payWay       支付方�?编�?
     * @param payWayName   支付方�?�??称
     * @param payType      支付类型
     * @param fundIntoType 资金�?入方�?�
     * @param feeRate      支付费率
     * @param orderIp      订�?�IP
     * @param returnUrl    页�?�通知地�?�
     * @param notifyUrl    �?��?�通知地�?�
     * @param remark       备注
     * @param field1       扩展字段1
     * @param field2       扩展字段2
     * @param field3       扩展字段3
     * @param field4       扩展字段4
     * @param field5       扩展字段5
     * @return
     */
    private RpTradePaymentRecord sealRpTradePaymentRecord(String merchantNo, String merchantName, String productName, String orderNo, BigDecimal orderPrice, String payWay, String payWayName, PayTypeEnum payType, String fundIntoType, BigDecimal feeRate, String orderIp, String returnUrl, String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5) {
        RpTradePaymentRecord rpTradePaymentRecord = new RpTradePaymentRecord();
        rpTradePaymentRecord.setProductName(productName);// 产�?�??称
        rpTradePaymentRecord.setMerchantOrderNo(orderNo);// 产�?编�?�

        String trxNo = buildNoService.buildTrxNo();
        rpTradePaymentRecord.setTrxNo(trxNo);// 支付�?水�?�

        String bankOrderNo = buildNoService.buildBankOrderNo();
        rpTradePaymentRecord.setBankOrderNo(bankOrderNo);// 银行订�?��?�
        rpTradePaymentRecord.setMerchantName(merchantName);
        rpTradePaymentRecord.setMerchantNo(merchantNo);// 商户编�?�
        rpTradePaymentRecord.setOrderIp(orderIp);// 下�?�IP
        rpTradePaymentRecord.setOrderRefererUrl("");// 下�?��?页�?�
        rpTradePaymentRecord.setReturnUrl(returnUrl);// 页�?�通知地�?�
        rpTradePaymentRecord.setNotifyUrl(notifyUrl);// �?��?�通知地�?�
        rpTradePaymentRecord.setPayWayCode(payWay);// 支付通�?�编�?
        rpTradePaymentRecord.setPayWayName(payWayName);// 支付通�?��??称
        rpTradePaymentRecord.setTrxType(TrxTypeEnum.EXPENSE.name());// 交易类型
        rpTradePaymentRecord.setOrderFrom(OrderFromEnum.USER_EXPENSE.name());// 订�?��?��?
        rpTradePaymentRecord.setOrderAmount(orderPrice);// 订�?�金�?
        rpTradePaymentRecord.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());// 订�?�状�?
        // 等待支付

        rpTradePaymentRecord.setPayTypeCode(payType.name());// 支付类型
        rpTradePaymentRecord.setPayTypeName(payType.getDesc());// 支付方�?
        rpTradePaymentRecord.setFundIntoType(fundIntoType);// 资金�?入方�?�

        if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(fundIntoType)) {// 平�?�收款
            // 需�?修改费率
            // �?本
            // 利润
            // 收入
            // 以�?�修改商户账户信�?�
            BigDecimal orderAmount = rpTradePaymentRecord.getOrderAmount();// 订�?�金�?
            BigDecimal platIncome = orderAmount.multiply(feeRate).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP); // 平�?�收入
            // =
            // 订�?�金�?
            // *
            // 支付费率(设置的费率除以100为真实费率)
            BigDecimal platCost = orderAmount.multiply(BigDecimal.valueOf(Double.valueOf(WeixinConfigUtil.readConfig("pay_rate")))).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);// 平�?��?本
            // =
            // 订�?�金�?
            // *
            // 微信费率(设置的费率除以100为真实费率)
            BigDecimal platProfit = platIncome.subtract(platCost);// 平�?�利润 = 平�?�收入
            // - 平�?��?本

            rpTradePaymentRecord.setFeeRate(feeRate);// 费率
            rpTradePaymentRecord.setPlatCost(platCost);// 平�?��?本
            rpTradePaymentRecord.setPlatIncome(platIncome);// 平�?�收入
            rpTradePaymentRecord.setPlatProfit(platProfit);// 平�?�利润

        }

        rpTradePaymentRecord.setRemark(remark);// 支付备注
        rpTradePaymentRecord.setField1(field1);// 扩展字段1
        rpTradePaymentRecord.setField2(field2);// 扩展字段2
        rpTradePaymentRecord.setField3(field3);// 扩展字段3
        rpTradePaymentRecord.setField4(field4);// 扩展字段4
        rpTradePaymentRecord.setField5(field5);// 扩展字段5
        return rpTradePaymentRecord;
    }

    /**
     * �?装预支付实体
     *
     * @param appId               公众�?�ID
     * @param mchId               商户�?�
     * @param productName         商�?�??述
     * @param remark              支付备注
     * @param bankOrderNo         银行订�?��?�
     * @param orderPrice          订�?�价格
     * @param orderTime           订�?�下�?�时间
     * @param orderPeriod         订�?�有效期
     * @param weiXinTradeTypeEnum 微信支付方�?
     * @param productId           商�?ID
     * @param openId              用户标识
     * @param orderIp             下�?�IP
     * @return
     */
    private WeiXinPrePay sealWeixinPerPay(String appId, String mchId, String productName, String remark, String bankOrderNo, BigDecimal orderPrice, Date orderTime, Integer orderPeriod, WeiXinTradeTypeEnum weiXinTradeTypeEnum, String productId, String openId, String orderIp) {
        WeiXinPrePay weiXinPrePay = new WeiXinPrePay();

        weiXinPrePay.setAppid(appId);
        weiXinPrePay.setMchId(mchId);
        weiXinPrePay.setBody(productName);// 商�?�??述
        weiXinPrePay.setAttach(remark);// 支付备注
        weiXinPrePay.setOutTradeNo(bankOrderNo);// 银行订�?��?�

        Integer totalFee = orderPrice.multiply(BigDecimal.valueOf(100d)).intValue();
        weiXinPrePay.setTotalFee(totalFee);// 订�?�金�?
        weiXinPrePay.setTimeStart(DateUtils.formatDate(orderTime, "yyyyMMddHHmmss"));// 订�?�开始时间
        weiXinPrePay.setTimeExpire(DateUtils.formatDate(DateUtils.addMinute(orderTime, orderPeriod), "yyyyMMddHHmmss"));// 订�?�到期时间
        weiXinPrePay.setNotifyUrl(WeixinConfigUtil.readConfig("notify_url"));// 通知地�?�
        weiXinPrePay.setTradeType(weiXinTradeTypeEnum);// 交易类型
        weiXinPrePay.setProductId(productId);// 商�?ID
        weiXinPrePay.setOpenid(openId);// 用户标识
        weiXinPrePay.setSpbillCreateIp(orderIp);// 下�?�IP

        return weiXinPrePay;
    }

    /**
     * 处�?�交易记录 如果交易记录是�?功或者本地未支付,查询上游已支付,返回TRUE 如果上游支付结果为未支付,返回FALSE
     *
     * @param bankOrderNo 银行订�?��?�
     * @return
     */
    @Override
    public boolean processingTradeRecord(String bankOrderNo) {
        RpTradePaymentRecord byBankOrderNo = rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
        if (byBankOrderNo == null) {
            LOG.info("�?存在该银行订�?��?�[{}]对应的交易记录", bankOrderNo);
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "�?�法订�?��?�");
        }
        LOG.info("订�?��?�:[{}],交易类型：[{}]", byBankOrderNo.getBankOrderNo(), byBankOrderNo.getPayWayCode());

        if (!TradeStatusEnum.WAITING_PAYMENT.name().equals(byBankOrderNo.getStatus())) {
            LOG.info("该银行订�?��?�[{}]对应的交易记录状�?为:{},�?需�?�?处�?�", bankOrderNo, byBankOrderNo.getStatus());
            return true;
        }

        // 微信
        if (byBankOrderNo.getPayWayCode().equals(PayWayEnum.WEIXIN.name())) {
            Map<String, Object> resultMap;
            if (PayTypeEnum.WX_PROGRAM_PAY.name().equals(byBankOrderNo.getPayTypeCode())) {
                LOG.info("微信--�?程�?订�?�查询!订�?��?�:[{}]", byBankOrderNo.getBankOrderNo());
                resultMap = WeiXinPayUtils.orderQuery(byBankOrderNo.getBankOrderNo(), WeixinConfigUtil.xAppId, WeixinConfigUtil.xMchId, WeixinConfigUtil.xPayKey);
            } else {
                LOG.info("微信--订�?�查询!订�?��?�:[{}]", byBankOrderNo.getBankOrderNo());
                resultMap = WeiXinPayUtils.orderQuery(byBankOrderNo.getBankOrderNo(), WeixinConfigUtil.appId, WeixinConfigUtil.mch_id, WeixinConfigUtil.partnerKey);
            }
            LOG.info("微信订�?�查询结果:{}", resultMap.toString());
            if (resultMap == null || resultMap.isEmpty()) {
                return false;
            }
            Object returnCode = resultMap.get("return_code");
            // 查询失败
            if (null == returnCode || "FAIL".equals(returnCode)) {
                return false;
            }
            // 当trade_state为SUCCESS时�?返回result_code
            if ("SUCCESS".equals(resultMap.get("trade_state"))) {
                completeSuccessOrder(byBankOrderNo, byBankOrderNo.getBankTrxNo(), new Date(), "订�?�交易�?功");
                return true;
            }
            return false;
        }

        //支付�?
        if (byBankOrderNo.getPayWayCode().equals(PayWayEnum.ALIPAY.name())) {
            if (PayTypeEnum.DIRECT_PAY.name().equals(byBankOrderNo.getPayTypeCode())) {
                //支付�?--�?�时到账
                LOG.info("支付�?--�?�时到账订�?�查询!订�?��?�:[{}]", byBankOrderNo.getBankOrderNo());
                Map<String, Object> resultMap = AliPayUtil.singleTradeQuery(byBankOrderNo.getBankOrderNo());
                if (resultMap.isEmpty() || !"T".equals(resultMap.get("is_success"))) {
                    return false;
                }
                // 当返回状�?为“TRADE_FINISHED�?交易�?功结�?�和“TRADE_SUCCESS�?支付�?功时更新交易状�?
                if ("TRADE_SUCCESS".equals(resultMap.get("trade_status")) || "TRADE_FINISHED".equals(resultMap.get("trade_status"))) {
                    completeSuccessOrder(byBankOrderNo, byBankOrderNo.getBankTrxNo(), new Date(), "订�?�交易�?功");
                    return true;
                }
            } else if (PayTypeEnum.F2F_PAY.name().equals(byBankOrderNo.getPayTypeCode())) {
                //支付�?--�?��?支付
                LOG.info("支付�?--�?��?支付订�?�查询!订�?��?�:[{}]", byBankOrderNo.getBankOrderNo());
                Map<String, Object> resultMap = AliPayUtil.tradeQuery(byBankOrderNo.getBankOrderNo());
                if (!"10000".equals(resultMap.get("code"))) {
                    return false;
                }
                // 当返回状�?为“TRADE_FINISHED�?交易�?功结�?�和“TRADE_SUCCESS�?支付�?功时更新交易状�?
                if ("TRADE_SUCCESS".equals(resultMap.get("tradeStatus")) || "TRADE_FINISHED".equals(resultMap.get("tradeStatus"))) {
                    completeSuccessOrder(byBankOrderNo, byBankOrderNo.getBankTrxNo(), new Date(), "订�?�交易�?功");
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public ProgramPayResultVo programPay(String payKey, String openId, String productName, String orderNo, Date orderDate, Date orderTime, BigDecimal orderPrice, String payWayCode, String orderIp, String notifyUrl, String remark, String field1, String field2, String field3, String field4, String field5) {
        RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByPayKey(payKey);
        if (rpUserPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        // 根�?�支付产�?�?�支付方�?获�?�费率
        RpPayWay payWay;
        PayTypeEnum payType;
        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
            payType = PayTypeEnum.WX_PROGRAM_PAY;
            payWay = rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(), payWayCode, payType.name());
        } else {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "暂�?支�?此支付方�?");
        }
        if (payWay == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }

        String merchantNo = rpUserPayConfig.getUserNo();// 商户编�?�
        RpUserInfo rpUserInfo = rpUserInfoService.getDataByMerchentNo(merchantNo);
        if (rpUserInfo == null) {
            throw new UserBizException(UserBizException.USER_IS_NULL, "用户�?存在");
        }

        //生产订�?�记录
        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo, orderNo);
        if (rpTradePaymentOrder == null) {
            rpTradePaymentOrder = sealRpTradePaymentOrder(merchantNo, rpUserInfo.getUserName(), productName, orderNo, orderDate, orderTime, orderPrice, payWayCode, PayWayEnum.getEnum(payWayCode).getDesc(), payType, rpUserPayConfig.getFundIntoType(), orderIp, 10, payType.name(), notifyUrl, remark, field1, field2, field3, field4, field5);
            rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
        } else {
            if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�已支付�?功,无需�?�?支付");
            }
            if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
                rpTradePaymentOrder.setOrderAmount(orderPrice);// 如果金�?�?一致,修改金�?为最新的金�?
            }
        }

        return getProgramPayResultVo(rpTradePaymentOrder, payWay, rpUserPayConfig.getPaySecret(), openId, null);
    }


    /**
     * 通过支付订�?��?�商户费率生�?支付记录
     *
     * @param tradePaymentOrder 支付订�?�
     * @param payWay            商户支付�?置
     * @return
     */
    private ProgramPayResultVo getProgramPayResultVo(RpTradePaymentOrder tradePaymentOrder, RpPayWay payWay, String merchantPaySecret, String openId, List<RoncooPayGoodsDetails> roncooPayGoodsDetailses) {

        ProgramPayResultVo resultVo = new ProgramPayResultVo();
        String payWayCode = payWay.getPayWayCode();// 支付方�?

        PayTypeEnum payType = null;
        if (PayWayEnum.WEIXIN.name().equals(payWay.getPayWayCode())) {
            payType = PayTypeEnum.WX_PROGRAM_PAY;
        } else if (PayWayEnum.ALIPAY.name().equals(payWay.getPayWayCode())) {
            // TODO 支付�?�?程�?支付，需�?自定义枚举
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "暂�?支�?此支付方�?");
        }

        tradePaymentOrder.setPayTypeCode(payType.name());// 支付类型
        tradePaymentOrder.setPayTypeName(payType.getDesc());// 支付方�?
        tradePaymentOrder.setPayWayCode(payWay.getPayWayCode());//支付通�?�编�?�
        tradePaymentOrder.setPayWayName(payWay.getPayWayName());//支付通�?��??称

        //生�?支付�?水
        RpTradePaymentRecord rpTradePaymentRecord = sealRpTradePaymentRecord(tradePaymentOrder.getMerchantNo(), tradePaymentOrder.getMerchantName(), tradePaymentOrder.getProductName(), tradePaymentOrder.getMerchantOrderNo(), tradePaymentOrder.getOrderAmount(), payWay.getPayWayCode(), payWay.getPayWayName(), payType, tradePaymentOrder.getFundIntoType(), BigDecimal.valueOf(payWay.getPayRate()), tradePaymentOrder.getOrderIp(), tradePaymentOrder.getReturnUrl(), tradePaymentOrder.getNotifyUrl(), tradePaymentOrder.getRemark(), tradePaymentOrder.getField1(), tradePaymentOrder.getField2(), tradePaymentOrder.getField3(), tradePaymentOrder.getField4(), tradePaymentOrder.getField5());
        rpTradePaymentRecordDao.insert(rpTradePaymentRecord);

        if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {// 微信支付
            Map<String, Object> resultMap = WeiXinPayUtil.appletPay(rpTradePaymentRecord.getBankOrderNo(), rpTradePaymentRecord.getProductName(), rpTradePaymentRecord.getOrderAmount(), rpTradePaymentRecord.getOrderIp(), WeixinConfigUtil.x_notify_url, openId, roncooPayGoodsDetailses);
            if (resultMap == null || resultMap.isEmpty()) {
                resultVo.setStatus(PublicEnum.NO.name());
                resultVo.setBankReturnMsg("请求支付失败!");
            } else {
                if ("YES".equals(resultMap.get("verify"))) {
                    if ("SUCCESS".equals(resultMap.get("return_code")) && "SUCCESS".equals(resultMap.get("result_code"))) {
                        resultVo.setStatus(PublicEnum.YES.name());
                        resultVo.setBankReturnMsg(String.valueOf(resultMap.get("return_msg")));

                        Object prepayId = resultMap.get("prepay_id");
                        Object appid = resultMap.get("appid");
                        SortedMap<String, Object> returnMap = new TreeMap<>();
                        returnMap.put("appId", appid);//appId
                        returnMap.put("timeStamp", System.currentTimeMillis());//当�?时间戳
                        returnMap.put("nonceStr", WeiXinPayUtil.getnonceStr());//�?机数
                        returnMap.put("package", "prepay_id=" + prepayId);//
                        returnMap.put("signType", "MD5");//签�??方�?
                        returnMap.put("paySign", WeiXinPayUtil.getSign(returnMap, WeixinConfigUtil.xPayKey));
                        returnMap.remove("appId");
                        String jsonString = JSON.toJSONString(returnMap);
                        resultVo.setPayMessage(jsonString);
                        //请求�?功，�?�起轮询
                        rpNotifyService.orderSend(rpTradePaymentRecord.getBankOrderNo());
                    } else {
                        resultVo.setStatus(PublicEnum.NO.name());
                        resultVo.setBankReturnMsg(String.valueOf(resultMap.get("return_msg")));
                    }


                } else {
                    resultVo.setStatus(PublicEnum.NO.name());
                    resultVo.setBankReturnMsg("请求微信返回信�?�验签�?通过�?");
                }
            }
        } else if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {// 支付�?支付
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, "暂�?支�?此支付方�?");
        }

        Map<String, Object> paramMap = new HashMap<>();
        if (!StringUtil.isEmpty(resultVo.getPayMessage())) {
            paramMap.put("payMessage", resultVo.getPayMessage());//支付信�?�
        }
        if (!StringUtil.isEmpty(resultVo.getBankReturnMsg())) {
            paramMap.put("bankReturnMsg", resultVo.getBankReturnMsg());
        }
        resultVo.setStatus(rpTradePaymentRecord.getStatus());// 支付结果
        paramMap.put("status", rpTradePaymentRecord.getStatus());
        String sign = MerchantApiUtil.getSign(paramMap, merchantPaySecret);
        resultVo.setSign(sign);
        return resultVo;
    }
}

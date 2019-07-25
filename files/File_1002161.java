package me.zhengjie.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import me.zhengjie.domain.AlipayConfig;
import me.zhengjie.domain.vo.TradeVo;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.repository.AlipayRepository;
import me.zhengjie.service.AlipayService;
import me.zhengjie.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * @author Zheng Jie
 * @date 2018-12-31
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    AlipayUtils alipayUtils;

    @Autowired
    private AlipayRepository alipayRepository;

    @Override
    public String toPayAsPC(AlipayConfig alipay, TradeVo trade) throws Exception {

        if(alipay.getId() == null){
            throw new BadRequestException("è¯·å…ˆæ·»åŠ ç›¸åº”é…?ç½®ï¼Œå†?æ“?ä½œ");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppID(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        double money = Double.parseDouble(trade.getTotalAmount());

        /**
         * åˆ›å»ºAPIå¯¹åº”çš„request(ç”µè„‘ç½‘é¡µç‰ˆ)
         */
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        /**
         * è®¢å?•å®Œæˆ?å?Žè¿”å›žçš„é¡µé?¢å’Œå¼‚æ­¥é€šçŸ¥åœ°å?€
         */
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        /**
         *  å¡«å……è®¢å?•å?‚æ•°
         */
        request.setBizContent("{" +
                "    \"out_trade_no\":\""+trade.getOutTradeNo()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+trade.getTotalAmount()+"," +
                "    \"subject\":\""+trade.getSubject()+"\"," +
                "    \"body\":\""+trade.getBody()+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipay.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }");//å¡«å……ä¸šåŠ¡å?‚æ•°
        /**
         * è°ƒç”¨SDKç”Ÿæˆ?è¡¨å?•
         * é€šè¿‡GETæ–¹å¼?ï¼Œå?£å?¯ä»¥èŽ·å?–url
         */
        return alipayClient.pageExecute(request, "GET").getBody();

    }

    @Override
    public String toPayAsWeb(AlipayConfig alipay, TradeVo trade) throws Exception {
        if(alipay.getId() == null){
            throw new BadRequestException("è¯·å…ˆæ·»åŠ ç›¸åº”é…?ç½®ï¼Œå†?æ“?ä½œ");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppID(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        double money = Double.parseDouble(trade.getTotalAmount());
        if(money <= 0 || money >= 5000){
            throw new BadRequestException("æµ‹è¯•é‡‘é¢?è¿‡å¤§");
        }

        /**
         * åˆ›å»ºAPIå¯¹åº”çš„request(æ‰‹æœºç½‘é¡µç‰ˆ)
         */
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

        /**
         * è®¢å?•å®Œæˆ?å?Žè¿”å›žçš„é¡µé?¢å’Œå¼‚æ­¥é€šçŸ¥åœ°å?€
         */
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        /**
         *  å¡«å……è®¢å?•å?‚æ•°
         */
        request.setBizContent("{" +
                "    \"out_trade_no\":\""+trade.getOutTradeNo()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+trade.getTotalAmount()+"," +
                "    \"subject\":\""+trade.getSubject()+"\"," +
                "    \"body\":\""+trade.getBody()+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipay.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }");//å¡«å……ä¸šåŠ¡å?‚æ•°
        /**
         * è°ƒç”¨SDKç”Ÿæˆ?è¡¨å?•
         * é€šè¿‡GETæ–¹å¼?ï¼Œå?£å?¯ä»¥èŽ·å?–url
         */
        return alipayClient.pageExecute(request, "GET").getBody();
    }

    @Override
    public AlipayConfig find() {
        Optional<AlipayConfig> alipayConfig = alipayRepository.findById(1L);
        if (alipayConfig.isPresent()){
            return alipayConfig.get();
        } else {
            return new AlipayConfig();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AlipayConfig update(AlipayConfig alipayConfig) {
        return alipayRepository.save(alipayConfig);
    }
}

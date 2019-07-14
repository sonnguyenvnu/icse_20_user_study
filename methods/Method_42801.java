@RequestMapping("/toPay/{orderNo}/{payWay}/{payKey}") public String toPay(@PathVariable("payKey") String payKey,@PathVariable("orderNo") String orderNo,@PathVariable("payWay") String payWay,Model model){
  ScanPayResultVo scanPayResultVo=rpTradePaymentManagerService.toNonDirectScanPay(payKey,orderNo,payWay);
  model.addAttribute("codeUrl",scanPayResultVo.getCodeUrl());
  if (PayWayEnum.WEIXIN.name().equals(scanPayResultVo.getPayWayCode())) {
    model.addAttribute("queryUrl",WeixinConfigUtil.readConfig("order_query_url") + "?orderNO=" + orderNo + "&payKey=" + payKey);
    model.addAttribute("productName",scanPayResultVo.getProductName());
    model.addAttribute("orderPrice",scanPayResultVo.getOrderAmount());
    return "weixinPayScanPay";
  }
 else   if (PayWayEnum.ALIPAY.name().equals(scanPayResultVo.getPayWayCode())) {
    return "alipayDirectPay";
  }
  return null;
}

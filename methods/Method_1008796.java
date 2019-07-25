@RequestMapping(value="/pay/{goodsOrderId}",method=RequestMethod.GET) @ResponseBody public String pay(@PathVariable("goodsOrderId") String goodsOrderId){
  GoodsOrder goodsOrder=goodsOrderService.getGoodsOrder(goodsOrderId);
  if (goodsOrder == null)   return "fail";
  int status=goodsOrder.getStatus();
  if (status != Constant.GOODS_ORDER_STATUS_INIT) {
    return "fail_001";
  }
  JSONObject paramMap=new JSONObject();
  paramMap.put("mchId",mchId);
  paramMap.put("mchOrderNo",goodsOrderId);
  paramMap.put("channelId","ALIPAY_WAP");
  paramMap.put("amount",goodsOrder.getAmount());
  paramMap.put("currency","cny");
  paramMap.put("clientIp","114.112.124.236");
  paramMap.put("device","WEB");
  paramMap.put("subject",goodsOrder.getGoodsName());
  paramMap.put("body",goodsOrder.getGoodsName());
  paramMap.put("notifyUrl",notifyUrl);
  paramMap.put("param1","");
  paramMap.put("param2","");
  paramMap.put("extra","{\"productId\":\"120989823\",\"openId\":\"o2RvowBf7sOVJf8kJksUEMceaDqo\"}");
  String reqSign=PayDigestUtil.getSign(paramMap,reqKey);
  paramMap.put("sign",reqSign);
  String reqData="params=" + paramMap.toJSONString();
  System.out.println("??????????,????:" + reqData);
  String url=baseUrl + "/pay/create_order?";
  String result=XXPayUtil.call4Post(url + reqData);
  System.out.println("??????????,????:" + result);
  Map retMap=JSON.parseObject(result);
  if ("SUCCESS".equals(retMap.get("retCode"))) {
    String checkSign=PayDigestUtil.getSign(retMap,resKey,"sign","payParams");
    String retSign=(String)retMap.get("sign");
    if (checkSign.equals(retSign)) {
      System.out.println("=========??????????=========");
    }
 else {
      System.err.println("=========??????????=========");
      return null;
    }
  }
  String payOrderId=retMap.get("payOrderId").toString();
  goodsOrder=new GoodsOrder();
  goodsOrder.setGoodsOrderId(goodsOrderId);
  goodsOrder.setPayOrderId(payOrderId);
  goodsOrder.setChannelId("ALIPAY_WAP");
  int ret=goodsOrderService.update(goodsOrder);
  _log.info("??????,??:{}",ret);
  return result + "";
}

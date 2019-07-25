@RequestMapping(value="/buy/{goodsId}",method=RequestMethod.GET) @ResponseBody public String buy(@PathVariable("goodsId") String goodsId){
  if (!"G_0001".equals(goodsId)) {
    return "fail";
  }
  String goodsOrderId=String.format("%s%s%06d","G",DateUtil.getSeqString(),(int)seq.getAndIncrement() % 1000000);
  GoodsOrder goodsOrder=new GoodsOrder();
  goodsOrder.setGoodsOrderId(goodsOrderId);
  goodsOrder.setGoodsId(goodsId);
  goodsOrder.setGoodsName("XXPAY????G_0001");
  goodsOrder.setAmount(1l);
  goodsOrder.setUserId("xxpay_000001");
  goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_INIT);
  int result=goodsOrderService.addGoodsOrder(goodsOrder);
  _log.info("??????,??:{}",result);
  return result + "";
}

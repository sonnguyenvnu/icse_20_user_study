@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE) public void receive(String message){
  log.info("receive message:" + message);
  MiaoshaMessage mm=RedisService.stringToBean(message,MiaoshaMessage.class);
  MiaoshaUser user=mm.getUser();
  long goodsId=mm.getGoodsId();
  ResultGeekQOrder<GoodsVoOrder> goodsVoOrderResultGeekQOrder=goodsServiceRpc.getGoodsVoByGoodsId(goodsId);
  if (!AbstractResultOrder.isSuccess(goodsVoOrderResultGeekQOrder)) {
    throw new GlobleException(ResultStatus.SESSION_ERROR);
  }
  GoodsVoOrder goods=goodsVoOrderResultGeekQOrder.getData();
  int stock=goods.getStockCount();
  if (stock <= 0) {
    return;
  }
  MiaoshaOrder order=orderService.getMiaoshaOrderByUserIdGoodsId(Long.valueOf(user.getNickname()),goodsId);
  if (order != null) {
    return;
  }
  miaoshaService.miaosha(user,goods);
}

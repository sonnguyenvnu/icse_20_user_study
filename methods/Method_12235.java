@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE) public void receive(String message){
  log.info("receive message:" + message);
  MiaoshaMessage mm=RedisService.stringToBean(message,MiaoshaMessage.class);
  MiaoshaUser user=mm.getUser();
  long goodsId=mm.getGoodsId();
  GoodsVo goods=goodsService.getGoodsVoByGoodsId(goodsId);
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

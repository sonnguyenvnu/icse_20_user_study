private boolean getGoodsOver(long goodsId){
  return redisService.exists(MiaoshaKey.isGoodsOver,"" + goodsId);
}

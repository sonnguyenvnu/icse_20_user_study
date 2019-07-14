/** 
 * ?????
 * @throws Exception
 */
@Override public void afterPropertiesSet() throws Exception {
  List<GoodsVo> goodsList=goodsService.listGoodsVo();
  if (goodsList == null) {
    return;
  }
  for (  GoodsVo goods : goodsList) {
    redisService.set(GoodsKey.getMiaoshaGoodsStock,"" + goods.getId(),goods.getStockCount());
    localOverMap.put(goods.getId(),false);
  }
}

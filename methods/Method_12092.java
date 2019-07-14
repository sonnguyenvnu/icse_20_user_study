public boolean reduceStock(GoodsVo goods){
  MiaoshaGoods g=new MiaoshaGoods();
  g.setGoodsId(goods.getId());
  int ret=goodsMapper.reduceStock(g);
  return ret > 0;
}

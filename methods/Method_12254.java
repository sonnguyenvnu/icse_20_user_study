public boolean reduceStock(GoodsVo goods){
  MiaoshaGoods g=new MiaoshaGoods();
  g.setGoodsId(goods.getId());
  int ret=goodsDao.reduceStock(g);
  return ret > 0;
}

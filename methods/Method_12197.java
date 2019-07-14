@Override public ResultGeekQOrder<GoodsVoOrder> getGoodsVoByGoodsId(long goodsId){
  ResultGeekQOrder<GoodsVoOrder> resultGeekQ=ResultGeekQOrder.build();
  try {
    GoodsVoOrder goodsVoOrder=goodsMapper.getGoodsVoByGoodsId(goodsId);
    resultGeekQ.setData(goodsVoOrder);
  }
 catch (  Exception e) {
    logger.error("?????????",e);
    resultGeekQ.withError(ResultStatusOrder.ORDER_GET_FAIL);
  }
  return resultGeekQ;
}

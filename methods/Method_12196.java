@Override public ResultGeekQOrder<List<GoodsVoOrder>> listGoodsVo(){
  ResultGeekQOrder<List<GoodsVoOrder>> resultGeekQ=ResultGeekQOrder.build();
  try {
    resultGeekQ.setData(goodsMapper.listGoodsVo());
  }
 catch (  Exception e) {
    logger.error("?????????",e);
    resultGeekQ.withError(ResultStatusOrder.ORDER_GET_FAIL);
  }
  return resultGeekQ;
}

@Override public ResultGeekQOrder<List<GoodsVoOrder>> listGoodsVo(){
  List<GoodsVoOrder> list1=new ArrayList<>();
  ResultGeekQOrder<List<GoodsVoOrder>> list=ResultGeekQOrder.build();
  list.setData(list1);
  return list;
}

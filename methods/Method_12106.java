public void closeOrder(int hour){
  Date closeDateTime=DateUtils.addHours(new Date(),-hour);
  List<OrderInfo> orderInfoList=orderMapper.selectOrderStatusByCreateTime(Integer.valueOf(ORDER_NOT_PAY.ordinal()),DateTimeUtils.dateToStr(closeDateTime));
  for (  OrderInfo orderInfo : orderInfoList) {
    System.out.println("orderinfo  infomation " + orderInfo.getGoodsName());
  }
}

public long getMiaoshaResult(Long userId,long goodsId){
  MiaoshaOrder order=orderService.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
  if (order != null) {
    return order.getOrderId();
  }
 else {
    boolean isOver=getGoodsOver(goodsId);
    if (isOver) {
      return -1;
    }
 else {
      return 0;
    }
  }
}

@RequestMapping("/detail") @ResponseBody public ResultGeekQ<OrderDetailVo> info(Model model,MiaoshaUser user,@RequestParam("orderId") long orderId){
  ResultGeekQ<OrderDetailVo> result=ResultGeekQ.build();
  if (user == null) {
    result.withError(SESSION_ERROR.getCode(),SESSION_ERROR.getMessage());
    return result;
  }
  OrderInfo order=orderService.getOrderById(orderId);
  if (order == null) {
    result.withError(ORDER_NOT_EXIST.getCode(),ORDER_NOT_EXIST.getMessage());
    return result;
  }
  long goodsId=order.getGoodsId();
  GoodsVo goods=goodsService.getGoodsVoByGoodsId(goodsId);
  OrderDetailVo vo=new OrderDetailVo();
  vo.setOrder(order);
  vo.setGoods(goods);
  result.setData(vo);
  return result;
}

/** 
 * ??????????????try????????
 * @param count  ????
 * @param amount ????
 * @return string
 */
@Override public String mockInventoryWithTryException(Integer count,BigDecimal amount){
  final Order order=buildOrder(count,amount);
  final int rows=orderMapper.save(order);
  if (rows > 0) {
    paymentService.mockPaymentInventoryWithTryException(order);
  }
  return "success";
}

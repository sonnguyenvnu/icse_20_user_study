/** 
 * ??????????????Confirm??????
 * @param count  ????
 * @param amount ????
 * @return string
 */
@Override public String mockInventoryWithConfirmException(Integer count,BigDecimal amount){
  final Order order=buildOrder(count,amount);
  final int rows=orderMapper.save(order);
  if (rows > 0) {
    paymentService.mockPaymentInventoryWithConfirmException(order);
  }
  return "success";
}

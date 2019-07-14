/** 
 * ??????????????try????timeout
 * @param count  ????
 * @param amount ????
 * @return string
 */
@Override @Transactional public String mockInventoryWithTryTimeout(Integer count,BigDecimal amount){
  final Order order=buildOrder(count,amount);
  final int rows=orderMapper.save(order);
  if (rows > 0) {
    paymentService.mockPaymentInventoryWithTryTimeout(order);
  }
  return "success";
}

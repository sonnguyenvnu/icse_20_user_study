/** 
 * Converts long amount into a BigMoney amount
 * @param price
 * @return
 */
public static BigDecimal getAmount(long price){
  return new BigDecimal(price).divide(new BigDecimal(BlockchainUtils.AMOUNT_INT_2_DECIMAL_FACTOR));
}

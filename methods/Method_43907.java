/** 
 * Returns the amount of the <code>currency</code> in this balance that is available to trade.
 * @return the amount that is available to trade.
 */
public BigDecimal getAvailable(){
  if (available == null) {
    return total.subtract(frozen).subtract(loaned).add(borrowed).subtract(withdrawing).subtract(depositing);
  }
 else {
    return available;
  }
}

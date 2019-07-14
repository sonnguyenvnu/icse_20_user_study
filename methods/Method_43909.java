/** 
 * Returns the frozen amount of the <code>currency</code> in this balance that is locked in trading.
 * @return the amount that is locked in open orders.
 */
public BigDecimal getFrozen(){
  if (frozen == null) {
    return total.subtract(available);
  }
  return frozen;
}

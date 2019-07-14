/** 
 * Returns the amount of the <code>currency</code> in this balance that may be withdrawn. Equal to <code>available - borrowed</code>.
 * @return the amount that is available to withdraw.
 */
public BigDecimal getAvailableForWithdrawal(){
  return getAvailable().subtract(getBorrowed());
}

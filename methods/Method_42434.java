/** 
 * ?????????
 * @return
 */
public BigDecimal getAvailableSettAmount(){
  BigDecimal subSettAmount=this.settAmount.subtract(unbalance);
  if (getAvailableBalance().compareTo(subSettAmount) == -1) {
    return getAvailableBalance();
  }
  return subSettAmount;
}

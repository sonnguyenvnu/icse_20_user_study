/** 
 * ?????????????? ????????????
 * @param lastMoney
 * @param count
 * @return
 */
private int checkMoney(int lastMoney,int count){
  double avg=lastMoney / count;
  if (avg < MIN_MONEY) {
    return LESS;
  }
  if (avg > MAX_MONEY) {
    return MORE;
  }
  return OK;
}

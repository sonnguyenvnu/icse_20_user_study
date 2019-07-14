/** 
 * ??????
 */
public int getAppRunDays(){
  if (createTime == null) {
    return -1;
  }
  Date now=new Date();
  long diff=now.getTime() - createTime.getTime();
  return (int)(diff / TimeUnit.DAYS.toMillis(1));
}

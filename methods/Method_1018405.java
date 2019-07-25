/** 
 * ????? ????
 * @return ???????
 */
public boolean lock(){
  lockValue=UUID.randomUUID().toString();
  String result=set(lockKey,lockValue,expireTime);
  locked=OK.equalsIgnoreCase(result);
  return locked;
}

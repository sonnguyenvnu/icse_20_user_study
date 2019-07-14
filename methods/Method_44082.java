/** 
 * assume the token is expired 1 minute before the actual expiry 
 */
public boolean valid(){
  return expireTime * 1000 - System.currentTimeMillis() > TimeUnit.MINUTES.toMillis(1);
}

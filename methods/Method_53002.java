/** 
 * @param auth authorization
 * @return an instance
 */
public AsyncTwitter getInstance(Authorization auth){
  return new AsyncTwitterImpl(conf,auth);
}

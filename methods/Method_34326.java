/** 
 * @return true if this request is known to be for a token to be refreshed
 */
public boolean isRefresh(){
  return refresh != null;
}

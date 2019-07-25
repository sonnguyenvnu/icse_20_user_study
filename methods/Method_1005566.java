/** 
 * ???????
 * @param path         path
 * @param target       ??ActivityClassName?ActivityClass?UriHandler
 * @param exported     ????????
 * @param interceptors ????interceptor
 */
public void register(String path,Object target,boolean exported,UriInterceptor... interceptors){
  if (!TextUtils.isEmpty(path)) {
    path=RouterUtils.appendSlash(path);
    UriHandler parse=UriTargetTools.parse(target,exported,interceptors);
    UriHandler prev=mMap.put(path,parse);
    if (prev != null) {
      Debugger.fatal("[%s] ????path='%s'?UriHandler: %s, %s",this,path,prev,parse);
    }
  }
}

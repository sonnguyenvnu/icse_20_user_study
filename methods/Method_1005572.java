/** 
 * ???????
 * @param regex        ?????
 * @param target       ??ActivityClassName?ActivityClass?UriHandler
 * @param exported     ????????
 * @param priority     ???
 * @param interceptors ????interceptor
 */
public void register(String regex,Object target,boolean exported,int priority,UriInterceptor... interceptors){
  Pattern pattern=compile(regex);
  if (pattern != null) {
    UriHandler innerHandler=UriTargetTools.parse(target,exported,interceptors);
    if (innerHandler != null) {
      RegexWrapperHandler handler=new RegexWrapperHandler(pattern,priority,innerHandler);
      addChildHandler(handler,priority);
    }
  }
}

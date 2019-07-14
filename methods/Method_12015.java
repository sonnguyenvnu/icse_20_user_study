/** 
 * Returns a  {@link Statement} that invokes {@code method} on {@code test}
 */
protected Statement methodInvoker(FrameworkMethod method,Object test){
  return new InvokeMethod(method,test);
}

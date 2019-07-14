/** 
 * Reads method name and appends it. Creates object for next call and returns that value. If next object is unsupported, it will return null;
 */
public Object execute(){
  String methodName=targetMethodName();
  Class returnType=returnType();
  Object next=pathref.continueWith(this,methodName,returnType);
  return ProxyTarget.returnValue(next);
}

/** 
 * Reads method name and stores it in local variable. For methods that return <code>String</code> returns the method name, otherwise returns <code>null</code>.
 */
@Override public Object execute(){
  methodName=targetMethodName();
  final Class returnType=returnType();
  methodCount++;
  if (returnType == String.class) {
    return ProxyTarget.returnValue(targetMethodName());
  }
  return ProxyTarget.returnValue(null);
}

public static Object fallback(String intfName,String methodName,Object[] args){
  ClientParsed parsed=ClientParser.get(intfName);
  if (parsed.getFallback() == null)   return null;
  Method method=parsed.getFallbackMethodMap().get(methodName);
  if (method == null)   return null;
  try {
    if (method.getReturnType() == void.class) {
      method.invoke(parsed.getFallback(),args);
      return null;
    }
    return method.invoke(parsed.getFallback(),args);
  }
 catch (  Exception e) {
    e.printStackTrace();
    throw new RuntimeException("Exception of fallback: " + intfName + "." + methodName);
  }
}

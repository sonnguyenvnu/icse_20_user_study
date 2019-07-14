/** 
 * ??????Class
 * @param className  ??
 * @param initialize ?????
 * @return Class
 */
public static Class forName(String className,boolean initialize){
  try {
    return Class.forName(className,initialize,getCurrentClassLoader());
  }
 catch (  Exception e) {
    throw new SofaRpcRuntimeException(e);
  }
}

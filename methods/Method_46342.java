/** 
 * ????Tracer
 * @return ????
 */
public static boolean isEnable(){
  String traceName=RpcConfigs.getStringValue(RpcOptions.DEFAULT_TRACER);
  return "sofaTracer".equals(traceName);
}

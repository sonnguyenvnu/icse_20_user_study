/** 
 * Prepares a list of service names deployed in current runtime
 * @param msgCtx
 * @return
 */
private static String _XXXXX_(MessageContext msgCtx){
  Map services=msgCtx.getConfigurationContext().getAxisConfiguration().getServices();
  StringBuffer sb=new StringBuffer();
  if (services != null && services.size() > 0) {
    Iterator itrServiceNames=services.keySet().iterator();
    int index=1;
    while (itrServiceNames.hasNext()) {
      String serviceName=(String)itrServiceNames.next();
      sb.append(index + "." + serviceName+ "\n");
      index++;
    }
  }
  return sb.toString();
}
/** 
 * ???????
 * @return ?????
 */
@VisibleForTesting static String parseHostMachine(){
  String hostMachine=System.getProperty("host_machine");
  return StringUtils.isNotEmpty(hostMachine) ? hostMachine : null;
}

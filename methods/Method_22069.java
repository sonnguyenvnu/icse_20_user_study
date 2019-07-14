/** 
 * ??????????????.
 * @return ????????????
 */
public boolean hasAvailableServers(){
  List<String> servers=jobNodeStorage.getJobNodeChildrenKeys(ServerNode.ROOT);
  for (  String each : servers) {
    if (isAvailableServer(each)) {
      return true;
    }
  }
  return false;
}

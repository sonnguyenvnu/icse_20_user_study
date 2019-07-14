/** 
 * ??clusterinfo????
 * @param clusterInfo
 * @return
 */
private Map<String,Object> processClusterInfoStats(String clusterInfo){
  Map<String,Object> clusterInfoMap=new HashMap<String,Object>();
  String[] lines=clusterInfo.split("\r\n");
  for (  String line : lines) {
    String[] pair=line.split(":");
    if (pair.length == 2) {
      clusterInfoMap.put(pair[0],pair[1]);
    }
  }
  return clusterInfoMap;
}

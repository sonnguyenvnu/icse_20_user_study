/** 
 * parse net to map
 * @param netString
 * @param subnetMap
 * @param isIn
 */
private void addNetMap(String netString,Map<String,NetChart> subnetMap,boolean isIn){
  String[] subnetArray=netString.split(";");
  for (  String subnet : subnetArray) {
    if (StringUtils.isEmpty(subnet)) {
      continue;
    }
    String[] net=subnet.split(",");
    NetChart netChart=subnetMap.get(net[0]);
    if (netChart == null) {
      netChart=new NetChart(net[0]);
      subnetMap.put(net[0],netChart);
    }
    float v=NumberUtils.toFloat(net[1]);
    if (isIn) {
      netChart.addInSeries(v);
      netChart.addTotalIn(v);
      netChart.setMaxIn(v);
    }
 else {
      netChart.addOutSeries(v);
      netChart.addTotalOut(v);
      netChart.setMaxOut(v);
    }
  }
}

private ClusterCreateInfo transform(ClusterCreateInfo clusterCreateInfo,DC_TRANSFORM_DIRECTION direction){
  List<String> dcs=clusterCreateInfo.getDcs();
  List<String> trans=new LinkedList<>();
  for (  String dc : dcs) {
    String transfer=direction.transform(dc);
    if (!Objects.equals(transfer,dc)) {
      logger.info("[transform]{}->{}",dc,transfer);
    }
    trans.add(transfer);
  }
  clusterCreateInfo.setDcs(trans);
  return clusterCreateInfo;
}

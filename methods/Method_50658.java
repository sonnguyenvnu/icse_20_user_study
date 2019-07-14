@Override public String getImage(){
  String apexName=node.getDefiningType().getApexName();
  return apexName.substring(apexName.lastIndexOf('.') + 1);
}

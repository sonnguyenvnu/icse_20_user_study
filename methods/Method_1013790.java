@Override public String output(){
  removeNextNodes();
  return option().name() + " " + StringUtil.join(WHITE_SPACE,nodes);
}

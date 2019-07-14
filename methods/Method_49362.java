private String getFullPath(ConfigOption<?> opt){
  StringBuilder sb=new StringBuilder(64);
  sb.insert(0,opt.getName());
  for (ConfigNamespace parent=opt.getNamespace(); null != parent && !parent.isRoot(); parent=parent.getNamespace()) {
    if (parent.isUmbrella())     sb.insert(0,"[X].");
    sb.insert(0,".");
    sb.insert(0,parent.getName());
  }
  return sb.toString();
}

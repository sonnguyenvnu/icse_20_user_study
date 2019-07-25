public void parse(String str){
  if (debug)   log.debug("parse " + str);
  String[] ss=Strings.splitIgnoreBlank(str,";");
  for (  String s : ss) {
    Pair<String> p=Pair.create(Strings.trim(s));
    if (p.getValueString() == null)     continue;
    if ("Path".equals(p.getName()) || "Expires".equals(p.getName()))     continue;
    if ("Max-Age".equals(p.getName())) {
      long age=Long.parseLong(p.getValue());
      if (age == 0)       return;
    }
    String val=p.getValueString();
    if (debug)     log.debugf("add cookie [%s=%s]",p.getName(),val);
    map.put(p.getName(),val);
  }
}

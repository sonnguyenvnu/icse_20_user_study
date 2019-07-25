public String check(String key){
  String v=get(key);
  if (Strings.isBlank(v)) {
    throw Er.create("e.cmd.lack.param",key);
  }
  return v;
}

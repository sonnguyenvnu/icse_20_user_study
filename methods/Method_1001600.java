@Override protected void append(String s,boolean checkConsistence){
  if (checkConsistence && s.indexOf("  ") != -1) {
    throw new IllegalArgumentException(s);
  }
  data.add(new PostScriptCommandRaw(s,checkConsistence));
}

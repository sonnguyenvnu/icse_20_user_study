static public int getSetupStart(String code){
  Pattern p=Pattern.compile("void[\\s\\t\\r\\n]*setup[\\s\\t]*\\(\\)[\\s\\t\\r\\n]*\\{");
  Matcher m=p.matcher(code);
  if (m.find()) {
    return m.end();
  }
  return -1;
}

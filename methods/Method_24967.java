static public int getVoidFunctionStart(String code,String functionName){
  Pattern p=Pattern.compile("void[\\s\\t\\r\\n]*" + functionName + "[\\s\\t]*\\(\\)[\\s\\t\\r\\n]*\\{");
  Matcher m=p.matcher(code);
  if (m.find()) {
    return m.end();
  }
  return -1;
}

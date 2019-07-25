private static String append(PrintWriter pw,String prefix,String newPrefix,String template,Object value,Object defaultValue){
  if (defaultValue.equals(value) || ("null".equals(defaultValue) && value == null)) {
    return prefix;
  }
  pw.print(prefix);
  pw.printf(template,value);
  return newPrefix;
}

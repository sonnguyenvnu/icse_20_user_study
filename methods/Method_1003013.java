private String[] tokenize(){
  ArrayList<String> list=new ArrayList<>();
  syntax=StringUtils.replaceAll(syntax,"yyyy-MM-dd","@ymd@");
  syntax=StringUtils.replaceAll(syntax,"hh:mm:ss","@hms@");
  syntax=StringUtils.replaceAll(syntax,"hh:mm","@hms@");
  syntax=StringUtils.replaceAll(syntax,"mm:ss","@hms@");
  syntax=StringUtils.replaceAll(syntax,"nnnnnnnnn","@nanos@");
  syntax=StringUtils.replaceAll(syntax,"function","@func@");
  syntax=StringUtils.replaceAll(syntax,"0x","@hexStart@");
  syntax=StringUtils.replaceAll(syntax,",...","@commaDots@");
  syntax=StringUtils.replaceAll(syntax,"...","@dots@");
  syntax=StringUtils.replaceAll(syntax,"||","@concat@");
  syntax=StringUtils.replaceAll(syntax,"a-z|_","@az_@");
  syntax=StringUtils.replaceAll(syntax,"A-Z|_","@az_@");
  syntax=StringUtils.replaceAll(syntax,"A-F","@af@");
  syntax=StringUtils.replaceAll(syntax,"0-9","@digit@");
  syntax=StringUtils.replaceAll(syntax,"'['","@openBracket@");
  syntax=StringUtils.replaceAll(syntax,"']'","@closeBracket@");
  StringTokenizer tokenizer=getTokenizer(syntax);
  while (tokenizer.hasMoreTokens()) {
    String s=tokenizer.nextToken();
    s=StringUtils.cache(s);
    if (s.length() == 1) {
      if (" \r\n".indexOf(s.charAt(0)) >= 0) {
        continue;
      }
    }
    list.add(s);
  }
  return list.toArray(new String[0]);
}

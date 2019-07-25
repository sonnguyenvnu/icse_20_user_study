public static Pair<String> create(String s){
  String[] ss=Strings.splitIgnoreBlank(s,"=");
  String name=null;
  String value=null;
  String pattern=PTN_3;
  if (null != ss)   if (ss.length == 1) {
    name=ss[0];
  }
 else   if (ss.length == 2) {
    name=ss[0];
    if (ss[1].length() > 0) {
      if (ss[1].charAt(0) == '"') {
        value=ss[1].substring(1,ss[1].length() - 1);
        pattern=PTN_3;
      }
 else       if (ss[1].charAt(0) == '\'') {
        value=ss[1].substring(1,ss[1].length() - 1);
        pattern=PTN_2;
      }
 else {
        value=ss[1];
        pattern=PTN_1;
      }
    }
  }
  Pair<String> re=new Pair<String>(name,value);
  re.pattern=pattern;
  return re;
}

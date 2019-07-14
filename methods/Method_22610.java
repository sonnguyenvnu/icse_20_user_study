public SyntaxStyle getStyle(String attribute){
  String str=Preferences.get("editor.token." + attribute + ".style");
  if (str == null) {
    throw new IllegalArgumentException("No style found for " + attribute);
  }
  StringTokenizer st=new StringTokenizer(str,",");
  String s=st.nextToken();
  if (s.indexOf("#") == 0)   s=s.substring(1);
  Color color=new Color(Integer.parseInt(s,16));
  s=st.nextToken();
  boolean bold=(s.indexOf("bold") != -1);
  return new SyntaxStyle(color,bold);
}

private HtmlColor build(String s){
  s=removeFirstDieseAndgoLowerCase(s);
  final Color color;
  if (s.equalsIgnoreCase("transparent")) {
    return new HtmlColorTransparent();
  }
 else   if (s.equalsIgnoreCase("automatic")) {
    return new HtmlColorAutomatic();
  }
 else   if (s.matches("[0-9A-Fa-f]{3}")) {
    s="" + s.charAt(0) + s.charAt(0) + s.charAt(1) + s.charAt(1) + s.charAt(2) + s.charAt(2);
    color=new Color(Integer.parseInt(s,16));
  }
 else   if (s.matches("[0-9A-Fa-f]{6}")) {
    color=new Color(Integer.parseInt(s,16));
  }
 else {
    final String value=htmlNames.get(s);
    if (value == null) {
      throw new IllegalArgumentException(s);
    }
    color=new Color(Integer.parseInt(value.substring(1),16));
  }
  return new HtmlColorSimple(color,false);
}

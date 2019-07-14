static public Color getColor(String name){
  Color parsed=Color.GRAY;
  String s=get(name);
  if ((s != null) && (s.indexOf("#") == 0)) {
    try {
      parsed=new Color(Integer.parseInt(s.substring(1),16));
    }
 catch (    Exception e) {
    }
  }
  return parsed;
}

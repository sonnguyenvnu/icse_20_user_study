public Color getColor(String attribute){
  Color parsed=null;
  String s=get(attribute);
  if ((s != null) && (s.indexOf("#") == 0)) {
    try {
      int v=Integer.parseInt(s.substring(1),16);
      parsed=new Color(v);
    }
 catch (    Exception e) {
    }
  }
  return parsed;
}

public static Tuple<String,String> substring(String strColumn,int pos,int len,String valueName){
  String name="substring_" + random();
  if (valueName == null) {
    return new Tuple(name,"def " + name + " = doc['" + strColumn + "'].value.substring(" + pos + "," + len + ")");
  }
 else {
    return new Tuple(name,strColumn + ";def " + name + " = " + valueName + ".substring(" + pos + "," + len + ")");
  }
}

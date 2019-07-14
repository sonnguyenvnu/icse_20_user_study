private static String convertType(SQLExpr script){
  String[] variance=Util.expr2Object(script).toString().split(";");
  String newScript=variance[variance.length - 1];
  if (newScript.trim().startsWith("def ")) {
    String temp=newScript.trim().substring(4).split("=")[0].trim();
    return " if( " + temp + " instanceof String) " + temp + "= Double.parseDouble(" + temp.trim() + "); ";
  }
 else   return "";
}

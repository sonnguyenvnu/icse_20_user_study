private static String extractName(SQLExpr script){
  if (isProperty(script))   return "doc['" + script + "'].value";
  String scriptStr=Util.expr2Object(script).toString();
  String[] variance=scriptStr.split(";");
  String newScript=variance[variance.length - 1];
  if (newScript.trim().startsWith("def ")) {
    return newScript.trim().substring(4).split("=")[0].trim();
  }
 else   return scriptStr;
}

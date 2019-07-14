public static Tuple<String,String> function(String methodName,List<KVValue> paramers,String name,boolean returnValue){
  Tuple<String,String> functionStr=null;
switch (methodName.toLowerCase()) {
case "if":
    String nameIF="";
  String caseString="";
if (paramers.get(0).value instanceof SQLInListExpr) {
  nameIF+=methodName + "(" + ((SQLInListExpr)paramers.get(0).value).getExpr() + " in (";
  String left="doc['" + ((SQLInListExpr)paramers.get(0).value).getExpr().toString() + "'].value";
  List<SQLExpr> targetList=((SQLInListExpr)paramers.get(0).value).getTargetList();
  for (  SQLExpr a : targetList) {
    caseString+=left + " == '" + a.toString() + "' ||";
    nameIF+=a.toString() + ",";
  }
  caseString=caseString.substring(0,caseString.length() - 2);
  nameIF=nameIF.substring(0,nameIF.length() - 1) + "),";
}
 else {
  String left="doc['" + paramers.get(0).key + "'].value";
  caseString+=left + " == '" + paramers.get(0).value + "'";
  nameIF=methodName + "(" + paramers.get(0).toString() + ",";
}
nameIF+=paramers.get(1).value + "," + paramers.get(2).value + ")";
functionStr=new Tuple<>(nameIF,"if((" + caseString + ")){" + paramers.get(1).value + "} else {" + paramers.get(2).value + "}");
break;
case "split":
if (paramers.size() == 3) {
functionStr=split(Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),Util.expr2Object((SQLExpr)paramers.get(1).value).toString(),Integer.parseInt(Util.expr2Object((SQLExpr)paramers.get(2).value).toString()),name);
}
 else {
functionStr=split(paramers.get(0).value.toString(),paramers.get(1).value.toString(),name);
}
break;
case "concat_ws":
List<SQLExpr> result=Lists.newArrayList();
for (int i=1; i < paramers.size(); i++) {
result.add((SQLExpr)paramers.get(i).value);
}
functionStr=concat_ws(paramers.get(0).value.toString(),result,name);
break;
case "date_format":
functionStr=date_format(Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),Util.expr2Object((SQLExpr)paramers.get(1).value).toString(),2 < paramers.size() ? Util.expr2Object((SQLExpr)paramers.get(2).value).toString() : null,name);
break;
case "abs":
case "round":
case "floor":
if (paramers.size() == 2) {
int decimalPrecision=Integer.parseInt(paramers.get(1).value.toString());
functionStr=mathRoundTemplate("Math." + methodName,methodName,Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),name,decimalPrecision);
break;
}
case "ceil":
case "cbrt":
case "rint":
case "exp":
case "sqrt":
functionStr=mathSingleValueTemplate("Math." + methodName,methodName,Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),name);
break;
case "pow":
functionStr=mathDoubleValueTemplate("Math." + methodName,methodName,Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),Util.expr2Object((SQLExpr)paramers.get(1).value).toString(),name);
break;
case "substring":
functionStr=substring(Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),Integer.parseInt(Util.expr2Object((SQLExpr)paramers.get(1).value).toString()),Integer.parseInt(Util.expr2Object((SQLExpr)paramers.get(2).value).toString()),name);
break;
case "trim":
functionStr=trim(Util.expr2Object((SQLExpr)paramers.get(0).value).toString(),name);
break;
case "add":
functionStr=add((SQLExpr)paramers.get(0).value,(SQLExpr)paramers.get(1).value);
break;
case "subtract":
functionStr=subtract((SQLExpr)paramers.get(0).value,(SQLExpr)paramers.get(1).value);
break;
case "divide":
functionStr=divide((SQLExpr)paramers.get(0).value,(SQLExpr)paramers.get(1).value);
break;
case "multiply":
functionStr=multiply((SQLExpr)paramers.get(0).value,(SQLExpr)paramers.get(1).value);
break;
case "modulus":
functionStr=modulus((SQLExpr)paramers.get(0).value,(SQLExpr)paramers.get(1).value);
break;
case "field":
functionStr=field(Util.expr2Object((SQLExpr)paramers.get(0).value).toString());
break;
case "log2":
functionStr=log(SQLUtils.toSQLExpr("2"),(SQLExpr)paramers.get(0).value,name);
break;
case "log10":
functionStr=log(SQLUtils.toSQLExpr("10"),(SQLExpr)paramers.get(0).value,name);
break;
case "log":
List<SQLExpr> logs=Lists.newArrayList();
for (int i=0; i < paramers.size(); i++) {
logs.add((SQLExpr)paramers.get(0).value);
}
if (logs.size() > 1) {
functionStr=log(logs.get(0),logs.get(1),name);
}
 else {
functionStr=log(SQLUtils.toSQLExpr("Math.E"),logs.get(0),name);
}
break;
default :
}
if (returnValue && !methodName.equalsIgnoreCase("if")) {
String generatedFieldName=functionStr.v1();
String returnCommand=";return " + generatedFieldName + ";";
String newScript=functionStr.v2() + returnCommand;
functionStr=new Tuple<>(generatedFieldName,newScript);
}
return functionStr;
}

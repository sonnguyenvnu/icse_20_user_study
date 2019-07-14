private static Tuple<String,String> mathRoundTemplate(String methodName,String fieldName,String strColumn,String valueName,int decimalPrecision){
  StringBuilder sb=new StringBuilder("1");
  for (int i=0; i < decimalPrecision; i++) {
    sb.append("0");
  }
  double num=Double.parseDouble(sb.toString());
  String name=fieldName + "_" + random();
  if (valueName == null) {
    return new Tuple<>(name,"def " + name + " = " + methodName + "((doc['" + strColumn + "'].value) * " + num + ")/" + num);
  }
 else {
    return new Tuple<>(name,strColumn + ";def " + name + " = " + methodName + "((" + valueName + ") * " + num + ")/" + num);
  }
}

private static Tuple<String,String> mathSingleValueTemplate(String methodName,String fieldName,String strColumn,String valueName){
  String name=fieldName + "_" + random();
  if (valueName == null) {
    return new Tuple<>(name,"def " + name + " = " + methodName + "(doc['" + strColumn + "'].value)");
  }
 else {
    return new Tuple<>(name,strColumn + ";def " + name + " = " + methodName + "(" + valueName + ")");
  }
}

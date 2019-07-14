public static Tuple<String,String> strSingleValueTemplate(String methodName,String strColumn,String valueName){
  String name=methodName + "_" + random();
  if (valueName == null) {
    return new Tuple(name,"def " + name + " = doc['" + strColumn + "'].value." + methodName + "()");
  }
 else {
    return new Tuple(name,strColumn + "; def " + name + " = " + valueName + "." + methodName + "()");
  }
}

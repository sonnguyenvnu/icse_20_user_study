public String report(){
  StringBuffer result=new StringBuffer();
  for (  String s : errors) {
    result.append(s).append("\n");
  }
  return result.toString();
}

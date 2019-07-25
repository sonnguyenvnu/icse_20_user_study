public String generate(){
  StringWriter result=new StringWriter();
  writeTo(new PrintWriter(result),"");
  return result.toString();
}

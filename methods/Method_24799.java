public static ImportStatement singleClass(String cls){
  ImportStatement is=new ImportStatement();
  int lastDot=cls.lastIndexOf('.');
  is.className=lastDot >= 0 ? cls.substring(lastDot + 1) : cls;
  is.packageName=lastDot >= 0 ? cls.substring(0,lastDot) : "";
  return is;
}

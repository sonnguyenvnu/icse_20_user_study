private static String addColorToCode(String code,String color){
  String end=null;
  int length=code.length();
  if (length > 2 && code.charAt(code.length() - 2) == '\u200D') {
    end=code.substring(code.length() - 2);
    code=code.substring(0,code.length() - 2);
  }
 else   if (length > 3 && code.charAt(code.length() - 3) == '\u200D') {
    end=code.substring(code.length() - 3);
    code=code.substring(0,code.length() - 3);
  }
  code+=color;
  if (end != null) {
    code+=end;
  }
  return code;
}

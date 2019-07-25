public static double strtod(CString str,CString[] endptr){
  final double result=Double.parseDouble(str.getContent());
  return result;
}

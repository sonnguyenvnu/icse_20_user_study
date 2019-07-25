public static int strtol(CString str,CString[] endptr,int base){
  if (base != 10) {
    throw new IllegalArgumentException();
  }
  CString end=str;
  int result=Integer.parseInt(end.getContent());
  endptr[0]=end.plus(("" + result).length());
  return result;
}

public static char[] convertIntToTwoChar(int n){
  char[] result=new char[2];
  result[0]=(char)(n >>> 16);
  result[1]=(char)(0x0000FFFF & n);
  return result;
}

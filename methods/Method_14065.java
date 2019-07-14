public static int getAxisDim(String type){
  return ("log".equals(type.toLowerCase())) ? LOG : LIN;
}

public static synchronized String next(){
  String result=ID_FORMAT.format(idCounter);
  idCounter++;
  return result;
}

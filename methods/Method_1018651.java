public static String hexdigest(String string){
  String s=null;
  try {
    s=hexdigest(string.getBytes());
  }
 catch (  Exception var3) {
    var3.printStackTrace();
  }
  return s;
}

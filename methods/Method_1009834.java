public static String waitforuser(String message){
  System.out.println(message);
  BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
  String s=null;
  try {
    while ((s=in.readLine()) != null && !(s.length() >= 0)) {
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return s;
}

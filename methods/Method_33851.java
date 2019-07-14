private static String getdevice(){
  String s=android.os.Build.MODEL;
  String t=s.replaceAll(" ","");
  return t;
}

@SuppressLint("PrivateApi") public static String getSystemProperty(String key){
  try {
    Class props=Class.forName("android.os.SystemProperties");
    return (String)props.getMethod("get",String.class).invoke(null,key);
  }
 catch (  Exception ignore) {
  }
  return null;
}

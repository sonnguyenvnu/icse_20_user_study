private static boolean isAndroid0(){
  try {
    Class.forName("android.app.Application",false,ClassLoaderUtil.getSystemClassLoader());
    return true;
  }
 catch (  Exception e) {
    return false;
  }
}

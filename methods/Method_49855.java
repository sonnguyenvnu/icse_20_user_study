private static int parsePort(String port){
  if (TextUtils.isEmpty(port)) {
    return 80;
  }
 else {
    return Integer.parseInt(port);
  }
}

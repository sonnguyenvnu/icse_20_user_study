public static String getPassword(URI uri){
  String userInfo=uri.getUserInfo();
  if (userInfo != null) {
    return userInfo.split(":",2)[1];
  }
  return null;
}

public static String encodeUserInfo(final String userInfo){
  return encodeUriComponent(userInfo,JoddCore.encoding,URIPart.USER_INFO);
}

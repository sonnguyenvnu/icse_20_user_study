private String getUserNameForTyping(TLRPC.User user){
  if (user == null) {
    return "";
  }
  if (user.first_name != null && user.first_name.length() > 0) {
    return user.first_name;
  }
 else   if (user.last_name != null && user.last_name.length() > 0) {
    return user.last_name;
  }
  return "";
}

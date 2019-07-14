private String formatUserSearchName(TLRPC.User user){
  StringBuilder str=new StringBuilder();
  if (user.first_name != null && user.first_name.length() > 0) {
    str.append(user.first_name);
  }
  if (user.last_name != null && user.last_name.length() > 0) {
    if (str.length() > 0) {
      str.append(" ");
    }
    str.append(user.last_name);
  }
  str.append(";;;");
  if (user.username != null && user.username.length() > 0) {
    str.append(user.username);
  }
  return str.toString().toLowerCase();
}

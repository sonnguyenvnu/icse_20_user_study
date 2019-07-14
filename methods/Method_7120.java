public String getGlobalNotificationsKey(int type){
  if (type == TYPE_GROUP) {
    return "EnableGroup2";
  }
 else   if (type == TYPE_PRIVATE) {
    return "EnableAll2";
  }
 else {
    return "EnableChannel2";
  }
}

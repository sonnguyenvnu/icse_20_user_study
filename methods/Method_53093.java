public static int toAccessLevel(HttpResponse res){
  if (null == res) {
    return -1;
  }
  String xAccessLevel=res.getResponseHeader("X-Access-Level");
  int accessLevel;
  if (null == xAccessLevel) {
    accessLevel=TwitterResponse.NONE;
  }
 else {
switch (xAccessLevel.length()) {
case 4:
      accessLevel=TwitterResponse.READ;
    break;
case 10:
  accessLevel=TwitterResponse.READ_WRITE;
break;
case 25:
accessLevel=TwitterResponse.READ_WRITE_DIRECTMESSAGES;
break;
case 26:
accessLevel=TwitterResponse.READ_WRITE_DIRECTMESSAGES;
break;
default :
accessLevel=TwitterResponse.NONE;
}
}
return accessLevel;
}

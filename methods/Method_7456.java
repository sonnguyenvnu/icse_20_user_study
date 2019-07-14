public String format(String orig){
  if (!initialzed) {
    return orig;
  }
  try {
    String str=strip(orig);
    if (str.startsWith("+")) {
      String rest=str.substring(1);
      CallingCodeInfo info=findCallingCodeInfo(rest);
      if (info != null) {
        String phone=info.format(rest);
        return "+" + phone;
      }
 else {
        return orig;
      }
    }
 else {
      CallingCodeInfo info=callingCodeInfo(defaultCallingCode);
      if (info == null) {
        return orig;
      }
      String accessCode=info.matchingAccessCode(str);
      if (accessCode != null) {
        String rest=str.substring(accessCode.length());
        String phone=rest;
        CallingCodeInfo info2=findCallingCodeInfo(rest);
        if (info2 != null) {
          phone=info2.format(rest);
        }
        if (phone.length() == 0) {
          return accessCode;
        }
 else {
          return String.format("%s %s",accessCode,phone);
        }
      }
 else {
        return info.format(str);
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return orig;
  }
}

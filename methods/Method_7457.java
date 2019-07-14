public boolean isPhoneNumberValid(String phoneNumber){
  if (!initialzed) {
    return true;
  }
  String str=strip(phoneNumber);
  if (str.startsWith("+")) {
    String rest=str.substring(1);
    CallingCodeInfo info=findCallingCodeInfo(rest);
    return info != null && info.isValidPhoneNumber(rest);
  }
 else {
    CallingCodeInfo info=callingCodeInfo(defaultCallingCode);
    if (info == null) {
      return false;
    }
    String accessCode=info.matchingAccessCode(str);
    if (accessCode != null) {
      String rest=str.substring(accessCode.length());
      if (rest.length() != 0) {
        CallingCodeInfo info2=findCallingCodeInfo(rest);
        return info2 != null && info2.isValidPhoneNumber(rest);
      }
 else {
        return false;
      }
    }
 else {
      return info.isValidPhoneNumber(str);
    }
  }
}

public static boolean checkPhonePattern(String pattern,String phone){
  if (TextUtils.isEmpty(pattern) || pattern.equals("*")) {
    return true;
  }
  String[] args=pattern.split("\\*");
  phone=PhoneFormat.stripExceptNumbers(phone);
  int checkStart=0;
  int index;
  for (int a=0; a < args.length; a++) {
    String arg=args[a];
    if (!TextUtils.isEmpty(arg)) {
      if ((index=phone.indexOf(arg,checkStart)) == -1) {
        return false;
      }
      checkStart=index + arg.length();
    }
  }
  return true;
}

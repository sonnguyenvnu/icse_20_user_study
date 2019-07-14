public static String inputPassToDBPass(String inputPass,String saltDB){
  String formPass=inputPassFormPass(inputPass);
  String dbPass=formPassToDBPass(formPass,saltDB);
  return dbPass;
}

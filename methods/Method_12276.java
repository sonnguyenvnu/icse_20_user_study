public static String inputPassToDbPass(String inputPass,String saltDB){
  String formPass=inputPassToFormPass(inputPass);
  String dbPass=formPassToDBPass(formPass,saltDB);
  return dbPass;
}

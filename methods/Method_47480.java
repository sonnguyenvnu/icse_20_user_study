public static String encode(String password){
  password=password + SALT;
  return processEncode(password);
}

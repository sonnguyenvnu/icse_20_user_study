private static boolean checkPassword(String pass){
  return sha256(pass).equals(Hawk.get("password_hash",null));
}

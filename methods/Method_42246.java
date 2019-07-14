public static boolean setPassword(String newValue){
  return Hawk.put("password_hash",sha256(newValue));
}

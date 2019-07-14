public static boolean clearPassword(){
  return Hawk.delete("password_hash");
}

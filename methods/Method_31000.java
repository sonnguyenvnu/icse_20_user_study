@NonNull public static byte[] base64ToByteArray(@NonNull String base64){
  return Base64.decode(base64,Base64.DEFAULT);
}

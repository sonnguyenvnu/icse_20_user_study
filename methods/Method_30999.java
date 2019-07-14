@NonNull public static String byteArrayToBase64(@NonNull byte[] bytes){
  return Base64.encodeToString(bytes,Base64.NO_WRAP);
}

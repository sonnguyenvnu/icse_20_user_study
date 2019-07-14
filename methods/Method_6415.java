public static long makeSecretDialogId(int chatId){
  return ((long)chatId) << 32;
}

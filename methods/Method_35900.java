private static boolean headerShouldBeTransferred(String key){
  return !ImmutableList.of(CONTENT_LENGTH,TRANSFER_ENCODING,"connection").contains(key.toLowerCase());
}

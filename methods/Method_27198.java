public static long toLong(@NonNull String text){
  if (!isEmpty(text)) {
    try {
      return Long.valueOf(text.replaceAll("[^0-9]",""));
    }
 catch (    NumberFormatException ignored) {
    }
  }
  return 0;
}

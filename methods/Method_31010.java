public static boolean equalsAny(@Nullable CharSequence text,CharSequence... array){
  for (  CharSequence element : array) {
    if (TextUtils.equals(text,element)) {
      return true;
    }
  }
  return false;
}

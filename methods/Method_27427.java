@NonNull private static String substring(@Nullable String value){
  if (value == null) {
    return "";
  }
  if (value.length() <= 7)   return value;
 else   return value.substring(0,7);
}

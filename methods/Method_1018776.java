public static String hash(CharSequence s){
  final Integer code=SPECIALS.get(s.toString());
  if (code != null) {
    return "&#" + code + ";";
  }
  return null;
}

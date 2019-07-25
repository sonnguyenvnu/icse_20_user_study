public final static boolean invisible(final char c){
  if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))   return false;
  final int type=Character.getType(c);
  return !(type == Character.LOWERCASE_LETTER || type == Character.DECIMAL_DIGIT_NUMBER || type == Character.UPPERCASE_LETTER || type == Character.MODIFIER_LETTER || type == Character.OTHER_LETTER || type == Character.TITLECASE_LETTER || punctuation(c));
}

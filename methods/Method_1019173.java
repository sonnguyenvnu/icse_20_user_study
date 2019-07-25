@NonNull @Override boolean encode(@NonNull SpannableStringBuilder ssb){
  boolean isHandledBackSlash=false;
  if (isContainsAsterisk) {
    isHandledBackSlash|=replace(ssb,SyntaxKey.KEY_ITALIC_BACKSLASH_ASTERISK,CharacterProtector.getKeyEncode());
  }
  if (isContainsUnderline) {
    isHandledBackSlash|=replace(ssb,SyntaxKey.KEY_ITALIC_BACKSLASH_UNDERLINE,CharacterProtector.getKeyEncode1());
  }
  return isHandledBackSlash;
}

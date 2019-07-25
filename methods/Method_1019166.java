@NonNull @Override boolean encode(@NonNull SpannableStringBuilder ssb){
  boolean isHandledBackSlash=false;
  isHandledBackSlash|=replace(ssb,SyntaxKey.KEY_HYPER_LINK_BACKSLASH_LEFT,CharacterProtector.getKeyEncode());
  isHandledBackSlash|=replace(ssb,SyntaxKey.KEY_HYPER_LINK_BACKSLASH_MIDDLE,CharacterProtector.getKeyEncode1());
  isHandledBackSlash|=replace(ssb,SyntaxKey.KEY_HYPER_LINK_BACKSLASH_RIGHT,CharacterProtector.getKeyEncode3());
  return isHandledBackSlash;
}

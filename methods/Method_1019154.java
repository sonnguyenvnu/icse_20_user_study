@NonNull @Override boolean encode(@NonNull SpannableStringBuilder ssb){
  return replace(ssb,SyntaxKey.KEY_CENTER_ALIGN_BACKSLASH_RIGHT,CharacterProtector.getKeyEncode1());
}

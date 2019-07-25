@NonNull @Override boolean encode(@NonNull SpannableStringBuilder ssb){
  return replace(ssb,SyntaxKey.KEY_CODE_BACKSLASH,CharacterProtector.getKeyEncode());
}

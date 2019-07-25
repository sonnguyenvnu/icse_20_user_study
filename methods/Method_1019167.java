@NonNull @Override void decode(@NonNull SpannableStringBuilder ssb){
  replace(ssb,CharacterProtector.getKeyEncode(),SyntaxKey.KEY_HYPER_LINK_BACKSLASH_LEFT);
  replace(ssb,CharacterProtector.getKeyEncode1(),SyntaxKey.KEY_HYPER_LINK_BACKSLASH_MIDDLE);
  replace(ssb,CharacterProtector.getKeyEncode3(),SyntaxKey.KEY_HYPER_LINK_BACKSLASH_RIGHT);
}

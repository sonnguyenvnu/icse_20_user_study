@NonNull @Override public List<EditToken> format(@NonNull Editable editable){
  return SyntaxUtils.parse(editable,PATTERN,SyntaxKey.KEY_CODE_BLOCK,this);
}

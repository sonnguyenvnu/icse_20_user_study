@NonNull @Override public List<EditToken> format(@NonNull Editable editable){
  return SyntaxUtils.parse(editable,PATTERN,this);
}

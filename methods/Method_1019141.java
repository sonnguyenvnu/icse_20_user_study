@NonNull @Override public CharSequence parse(@NonNull CharSequence charSequence,@NonNull MarkdownConfiguration markdownConfiguration){
  if (!(charSequence instanceof Editable)) {
    return charSequence;
  }
  if (markdownConfiguration == null) {
    return charSequence;
  }
  if (mSyntaxList == null || mMarkdownConfiguration == null || mMarkdownConfiguration != markdownConfiguration) {
    init(markdownConfiguration);
  }
  Editable editable=(Editable)charSequence;
  List<EditToken> list=new ArrayList<>();
  for (  Syntax syntax : mSyntaxList) {
    list.addAll(syntax.format(editable));
  }
  Editable newEditable=Editable.Factory.getInstance().newEditable(editable.toString());
  for (  EditToken editToken : list) {
    newEditable.setSpan(editToken.getSpan(),editToken.getStart(),editToken.getEnd(),editToken.getFlag());
  }
  return newEditable;
}

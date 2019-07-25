public CharSequence parse(CharSequence charSequence){
  MarkdownConfiguration config=getMarkdownConfiguration();
  return syntaxFactory.parse(charSequence,config);
}

@Override public LabelNode parse(String text){
  if (matcher.run(text)) {
    return new NamedLabelNode(matcher.get());
  }
  return fail(text,"Expected: <LABEL_TITLE>");
}

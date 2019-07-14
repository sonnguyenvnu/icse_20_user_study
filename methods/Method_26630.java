@Override public JCBreak inline(Inliner inliner){
  return inliner.maker().Break(ULabeledStatement.inlineLabel(getLabel(),inliner));
}

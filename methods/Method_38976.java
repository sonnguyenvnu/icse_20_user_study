@Override public void condComment(final CharSequence expression,final boolean isStartingTag,final boolean isHidden,final boolean isHiddenEndTag){
  for (  TagVisitor target : targets) {
    target.condComment(expression,isStartingTag,isHidden,isHiddenEndTag);
  }
}

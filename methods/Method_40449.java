@Override public boolean visit(Str n){
  String s=sourceString(n.start(),n.end());
  if (TRISTRING_PREFIX.matcher(s).lookingAt()) {
    addStyle(n.start(),n.end() - n.start(),StyleRun.Type.DOC_STRING);
    docOffsets.add(n.start());
    highlightDocString(n);
  }
  return true;
}

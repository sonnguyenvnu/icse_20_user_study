@Override public String evaluate(WalkedPath walkedPath){
  StringBuilder output=new StringBuilder();
  for (  Object token : tokens) {
    if (token instanceof String) {
      output.append(token);
    }
 else {
      AmpReference ref=(AmpReference)token;
      MatchedElement matchedElement=walkedPath.elementFromEnd(ref.getPathIndex()).getMatchedElement();
      String value=matchedElement.getSubKeyRef(ref.getKeyGroup());
      output.append(value);
    }
  }
  return output.toString();
}

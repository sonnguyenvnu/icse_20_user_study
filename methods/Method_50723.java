private ApexDocComment getApexDocComment(ApexNode<?> node){
  ASTFormalComment comment=node.getFirstChildOfType(ASTFormalComment.class);
  if (comment != null) {
    String token=comment.getToken();
    boolean hasDescription=DESCRIPTION_PATTERN.matcher(token).find();
    boolean hasReturn=RETURN_PATTERN.matcher(token).find();
    ArrayList<String> params=new ArrayList<>();
    Matcher paramMatcher=PARAM_PATTERN.matcher(token);
    while (paramMatcher.find()) {
      params.add(paramMatcher.group(1));
    }
    return new ApexDocComment(hasDescription,hasReturn,params);
  }
  return null;
}

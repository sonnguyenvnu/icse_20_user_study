private Description generateReturnFix(DocTreePath docTreePath,ReturnTree returnTree,VisitorState state){
  int pos=((DCDocComment)docTreePath.getDocComment()).comment.getSourcePos(0);
  String description=returnTree.toString().replaceAll("^@return ","");
  SuggestedFix fix=SuggestedFix.builder().merge(Utils.replace(returnTree,"",state)).replace(pos,pos,String.format("Returns %s%s\n",lowerFirstLetter(description),description.endsWith(".") ? "" : ".")).build();
  return buildDescription(diagnosticPosition(docTreePath,state)).setMessage(String.format(CONSIDER_USING_MESSAGE,"return")).addFix(fix).build();
}

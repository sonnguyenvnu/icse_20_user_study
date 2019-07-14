@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (state.isAndroidCompatible()) {
    return NO_MATCH;
  }
  if (STRING_GET_BYTES.matches(tree,state)) {
    Description.Builder description=buildDescription(tree);
    Tree parent=state.getPath().getParentPath().getLeaf();
    if (parent instanceof ExpressionTree && BYTESTRING_COPY_FROM.matches((ExpressionTree)parent,state)) {
      byteStringFixes(description,tree,(ExpressionTree)parent,state);
    }
 else {
      appendCharsets(description,tree,tree.getMethodSelect(),tree.getArguments(),state);
    }
    return description.build();
  }
  if (FILE_NEW_WRITER.matches(tree,state)) {
    Description.Builder description=buildDescription(tree);
    appendCharsets(description,tree,tree.getMethodSelect(),tree.getArguments(),state);
    return description.build();
  }
  return NO_MATCH;
}

private static ImmutableSet<String> extractDocumentedParams(DCDocComment docCommentTree,boolean isTypeParameter){
  ImmutableSet.Builder<String> parameters=ImmutableSet.builder();
  for (  DocTree docTree : docCommentTree.getBlockTags()) {
    if (!(docTree instanceof ParamTree)) {
      continue;
    }
    ParamTree paramTree=(ParamTree)docTree;
    if (paramTree.isTypeParameter() == isTypeParameter) {
      parameters.add(paramTree.getName().getName().toString());
    }
  }
  return parameters.build();
}

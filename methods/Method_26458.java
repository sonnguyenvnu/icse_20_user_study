@Override public Description matchTypeParameter(TypeParameterTree tree,VisitorState state){
  TypeParameterNamingClassification classification=TypeParameterNamingClassification.classify(tree.getName().toString());
  if (classification.isValidName()) {
    return Description.NO_MATCH;
  }
  Description.Builder descriptionBuilder=buildDescription(tree).setMessage(errorMessage(tree.getName(),classification));
  if (classification != TypeParameterNamingClassification.NON_CLASS_NAME_WITH_T_SUFFIX) {
    descriptionBuilder.addFix(TypeParameterShadowing.renameTypeVariable(tree,state.getPath().getParentPath().getLeaf(),suggestedNameFollowedWithT(tree.getName().toString()),state));
  }
  return descriptionBuilder.addFix(TypeParameterShadowing.renameTypeVariable(tree,state.getPath().getParentPath().getLeaf(),suggestedSingleLetter(tree.getName().toString(),tree),state)).build();
}

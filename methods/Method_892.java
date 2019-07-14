private boolean shouldSkip(List<ASTMemberValuePair> memberValuePairList){
  for (  ASTMemberValuePair pair : memberValuePairList) {
    String image=pair.getImage();
    if (image == null) {
      continue;
    }
    if (image.startsWith(ROLLBACK_PREFIX) || image.startsWith(READ_ONLY)) {
      return true;
    }
    ASTName name=pair.getFirstDescendantOfType(ASTName.class);
    if (name != null && PROPAGATION_NOT_SUPPORTED.equals(name.getImage())) {
      return true;
    }
  }
  return false;
}

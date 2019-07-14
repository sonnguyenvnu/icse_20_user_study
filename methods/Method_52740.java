public void openTag(ASTElement elm){
  if (elm == null || StringUtil.isEmpty(elm.getName())) {
    throw new IllegalStateException("Tried to open a tag with empty name");
  }
  tagList.add(elm);
}

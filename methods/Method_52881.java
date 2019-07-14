private boolean doesTagSupportEscaping(final ASTElement node){
  if (node.getName() == null) {
    return false;
  }
switch (node.getName().toLowerCase(Locale.ROOT)) {
case APEX_OUTPUT_TEXT:
case APEX_PAGE_MESSAGE:
case APEX_PAGE_MESSAGES:
case APEX_SELECT_OPTION:
    return true;
default :
  return false;
}
}

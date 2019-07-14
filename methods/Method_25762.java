private boolean shouldAutoboxFix(VisitorState state){
switch (state.getPath().getParentPath().getLeaf().getKind()) {
case METHOD_INVOCATION:
    return false;
case MEMBER_SELECT:
  return false;
case TYPE_CAST:
return false;
default :
return true;
}
}

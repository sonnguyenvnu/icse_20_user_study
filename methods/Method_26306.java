@Override public Description matchIf(IfTree outerIf,VisitorState state){
  DCLInfo info=findDCL(outerIf);
  if (info == null) {
    return Description.NO_MATCH;
  }
switch (info.sym().getKind()) {
case FIELD:
    return handleField(info.outerIf(),info.sym(),state);
case LOCAL_VARIABLE:
  return handleLocal(info,state);
default :
return Description.NO_MATCH;
}
}

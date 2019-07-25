public static boolean tag(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"tag"))   return false;
  if (!nextTokenIs(b,OPENING))   return false;
  boolean r, p;
  Marker m=enter_section_(b,l,_NONE_,TAG,null);
  r=consumeToken(b,OPENING);
  p=r;
  r=r && report_error_(b,tag_1(b,l + 1));
  r=p && consumeToken(b,CLOSING) && r;
  exit_section_(b,l,m,r,p,null);
  return r || p;
}

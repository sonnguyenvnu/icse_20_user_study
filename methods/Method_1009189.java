public static boolean tuple(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"tuple"))   return false;
  if (!nextTokenIs(b,OPENING_CURLY))   return false;
  boolean r;
  Marker m=enter_section_(b);
  r=consumeToken(b,OPENING_CURLY);
  r=r && termList(b,l + 1);
  r=r && consumeToken(b,CLOSING_CURLY);
  exit_section_(b,m,TUPLE,r);
  return r;
}

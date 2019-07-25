public static boolean struct(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"struct"))   return false;
  if (!nextTokenIs(b,MAP_OPERATOR))   return false;
  boolean r, p;
  Marker m=enter_section_(b,l,_NONE_,STRUCT,null);
  r=consumeTokens(b,3,MAP_OPERATOR,QUALIFIED_ALIAS,OPENING_CURLY);
  p=r;
  r=r && report_error_(b,struct_3(b,l + 1));
  r=p && consumeToken(b,CLOSING_CURLY) && r;
  exit_section_(b,l,m,r,p,null);
  return r || p;
}

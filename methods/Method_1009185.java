static boolean keyword(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"keyword"))   return false;
  boolean r, p;
  Marker m=enter_section_(b,l,_NONE_);
  r=consumeToken(b,KEY);
  p=r;
  r=r && term(b,l + 1);
  exit_section_(b,l,m,r,p,keywordRecoverWhile_parser_);
  return r || p;
}

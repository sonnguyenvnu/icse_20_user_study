public static boolean interpolation(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"interpolation"))   return false;
  if (!nextTokenIs(b,INTERPOLATION_START))   return false;
  boolean r;
  Marker m=enter_section_(b);
  r=consumeToken(b,INTERPOLATION_START);
  r=r && elixirFile(b,l + 1);
  r=r && consumeToken(b,INTERPOLATION_END);
  exit_section_(b,m,INTERPOLATION,r);
  return r;
}

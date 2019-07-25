public static boolean relative(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"relative"))   return false;
  boolean r;
  Marker m=enter_section_(b,l,_NONE_,RELATIVE,"<relative>");
  r=consumeToken(b,ATOM_KEYWORD);
  if (!r)   r=consumeToken(b,NAME);
  if (!r)   r=consumeToken(b,STRING);
  if (!r)   r=consumeToken(b,SYMBOLIC_OPERATOR);
  exit_section_(b,l,m,r,false,null);
  return r;
}

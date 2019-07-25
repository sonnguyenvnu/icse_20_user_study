public static boolean operands(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"operands"))   return false;
  boolean r;
  Marker m=enter_section_(b,l,_NONE_,OPERANDS,"<operands>");
  r=keywordList(b,l + 1);
  if (!r)   r=termList(b,l + 1);
  exit_section_(b,l,m,r,false,null);
  return r;
}

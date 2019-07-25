public static boolean eex(PsiBuilder b,int l){
  if (!recursion_guard_(b,l,"eex"))   return false;
  boolean r;
  Marker m=enter_section_(b,l,_NONE_,EEX,"<eex>");
  r=eex_0(b,l + 1);
  while (r) {
    int c=current_position_(b);
    if (!eex_0(b,l + 1))     break;
    if (!empty_element_parsed_guard_(b,"eex",c))     break;
  }
  exit_section_(b,l,m,r,false,null);
  return r;
}

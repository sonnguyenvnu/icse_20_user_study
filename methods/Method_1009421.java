@WebkitCall(from="editor") public void onscroll(Object pos,Object max){
  rightShowerHider.getShowing().ifPresent(s -> s.onscroll(pos,max));
}

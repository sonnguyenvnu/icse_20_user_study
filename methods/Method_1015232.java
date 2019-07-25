public Rope linear(){
  return isLinear() ? this : new Rope(root,true);
}

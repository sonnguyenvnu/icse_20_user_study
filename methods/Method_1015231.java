public Rope forked(){
  return isLinear() ? new Rope(root,false) : this;
}

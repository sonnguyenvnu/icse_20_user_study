MPSPsiNodeBase last(){
  return head != null && head.prev != null ? head.prev.node : null;
}

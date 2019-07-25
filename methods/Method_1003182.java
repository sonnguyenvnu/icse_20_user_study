private void replace(TreeNode x,TreeNode n){
  if (x == root) {
    root=n;
    if (n != null) {
      n.parent=null;
    }
  }
 else {
    set(x.parent,x.isFromLeft(),n);
  }
}

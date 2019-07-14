boolean containsTree(TreeNode t1,TreeNode t2){
  if (t2 == null) {
    return true;
  }
  return subTree(t1,t2);
}

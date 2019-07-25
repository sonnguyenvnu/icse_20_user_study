private static TreeNode child(TreeNode x,boolean isLeft){
  return isLeft ? x.left : x.right;
}

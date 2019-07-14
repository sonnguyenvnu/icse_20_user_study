public boolean isSymmetric(TreeNode root){
  if (root == null) {
    return true;
  }
  return helper(root.left,root.right);
}

public boolean helper(TreeNode left,TreeNode right){
  if (left == null && right == null) {
    return true;
  }
  if (left == null || right == null || left.val != right.val) {
    return false;
  }
  return helper(left.right,right.left) && helper(left.left,right.right);
}

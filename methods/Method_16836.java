public boolean isSymmetric(TreeNode root){
  if (root == null)   return true;
  LinkedList<TreeNode> q=new LinkedList<>();
  q.add(root.left);
  q.add(root.right);
  TreeNode left, right;
  while (q.size() > 1) {
    left=q.pop();
    right=q.pop();
    if (left == null && right == null)     continue;
    if (left == null || right == null)     return false;
    if (left.val != right.val)     return false;
    q.add(left.left);
    q.add(right.right);
    q.add(left.right);
    q.add(right.left);
  }
  return true;
}

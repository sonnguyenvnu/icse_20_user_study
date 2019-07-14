public void BSTIterator(TreeNode root){
  stack=new Stack<TreeNode>();
  while (root != null) {
    stack.push(root);
    root=root.left;
  }
}

private void dfs(TreeNode node,int height){
  if (node != null) {
    if (height > maxHeigh) {
      list.add(node.val);
      maxHeigh=height;
    }
    dfs(node.right,height + 1);
    dfs(node.left,height + 1);
  }
}

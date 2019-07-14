void findSum(TreeNode node,int sum,int[] path,int level){
  if (node == null) {
    return;
  }
  path[level]=node.data;
  int t=0;
  for (int i=level; i >= 0; i--) {
    t+=path[i];
    if (t == sum) {
      print(path,i,level);
    }
  }
  findSum(node.left,sum,path,level + 1);
  findSum(node.right,sum,path,level + 1);
  path[level]=Integer.MIN_VALUE;
}

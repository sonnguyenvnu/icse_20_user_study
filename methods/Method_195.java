public List<String> binaryTreePaths(TreeNode root){
  List<String> result=new ArrayList<String>();
  if (root == null) {
    return result;
  }
  helper(new String(),root,result);
  return result;
}

public void connect(TreeLinkNode root){
  if (root == null) {
    return;
  }
  Queue<TreeLinkNode> queue=new LinkedList<TreeLinkNode>();
  queue.add(root);
  while (!queue.isEmpty()) {
    Queue<TreeLinkNode> currentLevel=new LinkedList<TreeLinkNode>();
    TreeLinkNode temp=null;
    while (!queue.isEmpty()) {
      TreeLinkNode current=queue.remove();
      current.next=temp;
      temp=current;
      if (current.right != null) {
        currentLevel.add(current.right);
      }
      if (current.left != null) {
        currentLevel.add(current.left);
      }
    }
    queue=currentLevel;
  }
}

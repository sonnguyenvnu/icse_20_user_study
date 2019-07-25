public void connect(TreeLinkNode root){
  TreeLinkNode prev=new TreeLinkNode(0);
  TreeLinkNode first=null;
  while (root != null) {
    if (root.left != null) {
      prev.next=root.left;
      prev=root.left;
      if (first == null) {
        first=root.left;
      }
    }
    if (root.right != null) {
      prev.next=root.right;
      prev=root.right;
      if (first == null) {
        first=root.right;
      }
    }
    root=root.next;
    if (root == null) {
      root=first;
      first=null;
      prev=new TreeLinkNode(0);
    }
  }
}

@Override public boolean flush(){
  if (root != null && root.dirty) {
    encode();
    root=new Node(root.hash);
    return true;
  }
 else {
    return false;
  }
}

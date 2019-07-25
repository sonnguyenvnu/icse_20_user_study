private void swap(int node1,int node2){
  final int parent1=parent(node1);
  final int parent2=parent(node2);
  if (parent1 != NIL) {
    if (node1 == left(parent1)) {
      left(parent1,node2);
    }
 else {
      assert node1 == right(parent1);
      right(parent1,node2);
    }
  }
 else {
    assert root == node1;
    root=node2;
  }
  if (parent2 != NIL) {
    if (node2 == left(parent2)) {
      left(parent2,node1);
    }
 else {
      assert node2 == right(parent2);
      right(parent2,node1);
    }
  }
 else {
    assert root == node2;
    root=node1;
  }
  parent(node1,parent2);
  parent(node2,parent1);
  final int left1=left(node1);
  final int left2=left(node2);
  left(node1,left2);
  if (left2 != NIL) {
    parent(left2,node1);
  }
  left(node2,left1);
  if (left1 != NIL) {
    parent(left1,node2);
  }
  final int right1=right(node1);
  final int right2=right(node2);
  right(node1,right2);
  if (right2 != NIL) {
    parent(right2,node1);
  }
  right(node2,right1);
  if (right1 != NIL) {
    parent(right1,node2);
  }
  final int depth1=depth(node1);
  final int depth2=depth(node2);
  depth(node1,depth2);
  depth(node2,depth1);
}

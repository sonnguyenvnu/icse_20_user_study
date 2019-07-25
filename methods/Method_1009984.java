private void rebalance(int node){
  for (int n=node; n != NIL; ) {
    final int p=parent(n);
    fixAggregates(n);
switch (balanceFactor(n)) {
case -2:
      final int right=right(n);
    if (balanceFactor(right) == 1) {
      rotateRight(right);
    }
  rotateLeft(n);
break;
case 2:
final int left=left(n);
if (balanceFactor(left) == -1) {
rotateLeft(left);
}
rotateRight(n);
break;
case -1:
case 0:
case 1:
break;
default :
throw new AssertionError();
}
n=p;
}
}

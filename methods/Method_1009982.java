/** 
 * Update <code>node</code> with the current data.
 */
public void update(int node){
  final int prev=prev(node);
  final int next=next(node);
  if ((prev == NIL || compare(prev) > 0) && (next == NIL || compare(next) < 0)) {
    copy(node);
    for (int n=node; n != NIL; n=parent(n)) {
      fixAggregates(n);
    }
  }
 else {
    remove(node);
    add();
  }
}

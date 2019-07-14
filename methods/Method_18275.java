private static void resumeCreateTree(ComponentContext c,InternalNode root){
  final List<Component> unresolved=root.getUnresolvedComponents();
  if (unresolved != null) {
    for (int i=0, size=unresolved.size(); i < size; i++) {
      root.child(unresolved.get(i));
    }
    root.getUnresolvedComponents().clear();
  }
  for (int i=0, size=root.getChildCount(); i < size; i++) {
    resumeCreateTree(c,root.getChildAt(i));
  }
}

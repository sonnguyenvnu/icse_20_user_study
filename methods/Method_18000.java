/** 
 * @return String representation of the tree with the root at the passed node For example:PlaygroundComponent |-Text[trans.key="text_transition_key";] |-Row | +-Text +-Text[manual.key="text2";]
 */
static String treeToString(@Nullable InternalNode root){
  if (root == null) {
    return "null";
  }
  final StringBuilder builder=new StringBuilder();
  final Deque<InternalNode> stack=new LinkedList<>();
  stack.addLast(null);
  stack.addLast(root);
  int level=0;
  while (!stack.isEmpty()) {
    final InternalNode node=stack.removeLast();
    if (node == null) {
      level--;
      continue;
    }
    final Component component=node.getTailComponent();
    if (component == null) {
      continue;
    }
    if (node != root) {
      builder.append('\n');
      boolean isLast;
      final Iterator<InternalNode> iterator=stack.iterator();
      iterator.next();
      iterator.next();
      for (int index=0; index < level - 1; index++) {
        isLast=iterator.next() == null;
        if (!isLast) {
          while (iterator.next() != null)           ;
        }
        builder.append(isLast ? ' ' : "\u2502").append(' ');
      }
      builder.append(stack.getLast() == null ? "\u2514\u2574" : "\u251C\u2574");
    }
    builder.append(component.getSimpleName());
    if (component.hasManualKey() || node.hasTransitionKey() || node.getTestKey() != null) {
      builder.append('[');
      if (component.hasManualKey()) {
        builder.append("manual.key=\"").append(component.getKey()).append("\";");
      }
      if (node.hasTransitionKey()) {
        builder.append("trans.key=\"").append(node.getTransitionKey()).append("\";");
      }
      if (node.getTestKey() != null) {
        builder.append("test.key=\"").append(node.getTestKey()).append("\";");
      }
      builder.append(']');
    }
    if (node.getChildCount() == 0) {
      continue;
    }
    stack.addLast(null);
    for (int index=node.getChildCount() - 1; index >= 0; index--) {
      stack.addLast(node.getChildAt(index));
    }
    level++;
  }
  return builder.toString();
}

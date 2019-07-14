@Override protected ComponentLayout resolve(ComponentContext c){
  InternalNode node=c.newLayoutBuilder(0,0).flexDirection(reverse ? YogaFlexDirection.COLUMN_REVERSE : YogaFlexDirection.COLUMN);
  if (alignItems != null) {
    node.alignItems(alignItems);
  }
  if (alignContent != null) {
    node.alignContent(alignContent);
  }
  if (justifyContent != null) {
    node.justifyContent(justifyContent);
  }
  if (wrap != null) {
    node.wrap(wrap);
  }
  if (children != null) {
    for (    Component child : children) {
      if (c.wasLayoutCanceled()) {
        return ComponentContext.NULL_LAYOUT;
      }
      if (c.wasLayoutInterrupted()) {
        node.appendUnresolvedComponent(child);
      }
 else {
        node.child(child);
      }
    }
  }
  return node;
}

private static void performLayoutOnChildrenIfNecessary(ComponentHost host){
  for (int i=0, count=host.getChildCount(); i < count; i++) {
    final View child=host.getChildAt(i);
    if (child.isLayoutRequested()) {
      child.measure(MeasureSpec.makeMeasureSpec(child.getWidth(),MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(child.getHeight(),MeasureSpec.EXACTLY));
      child.layout(child.getLeft(),child.getTop(),child.getRight(),child.getBottom());
    }
    if (child instanceof ComponentHost) {
      performLayoutOnChildrenIfNecessary((ComponentHost)child);
    }
  }
}

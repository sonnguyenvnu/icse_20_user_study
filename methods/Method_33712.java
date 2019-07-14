public static void removeSelfFromParent(View child){
  if (child != null) {
    ViewParent parent=child.getParent();
    if (parent instanceof ViewGroup) {
      ViewGroup group=(ViewGroup)parent;
      group.removeView(child);
    }
  }
}

private void recursivelySetChildClippingForView(View view,boolean clipChildren){
  if (view instanceof ComponentHost) {
    if (clipChildren) {
      ((ComponentHost)view).restoreChildClipping();
    }
 else {
      ((ComponentHost)view).temporaryDisableChildClipping();
    }
  }
  final ViewParent parent=view.getParent();
  if (parent instanceof ComponentHost) {
    recursivelySetChildClippingForView((View)parent,clipChildren);
  }
}

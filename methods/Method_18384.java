private static void applyBoundsToMountContent(Object content,int left,int top,int right,int bottom,boolean force){
  assertMainThread();
  if (content instanceof View) {
    BoundsHelper.applyBoundsToView((View)content,left,top,right,bottom,force);
  }
 else   if (content instanceof Drawable) {
    ((Drawable)content).setBounds(left,top,right,bottom);
  }
 else {
    throw new IllegalStateException("Unsupported mounted content " + content);
  }
}

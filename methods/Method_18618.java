/** 
 * Set the clipChildren properties to all Views in the same tree branch from the given one, up to the top LithoView. TODO(17934271): Handle the case where two+ animations with different lifespans share the same parent, in which case we shouldn't unset clipping until the last item is done animating.
 */
private void recursivelySetChildClipping(Object mountContent,boolean clipChildren){
  if (!(mountContent instanceof View)) {
    return;
  }
  recursivelySetChildClippingForView((View)mountContent,clipChildren);
}

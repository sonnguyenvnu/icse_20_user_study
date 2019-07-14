/** 
 * Returns the Drawables associated with this ComponentHost for animations, for example the background Drawable and/or the drawable that otherwise has a transitionKey on it that has caused it to be hosted in this ComponentHost. <p>The core purpose of exposing these drawables is so that when animating the bounds of this ComponentHost, we also properly animate the bounds of its contained Drawables at the same time.
 */
public @Nullable List<Drawable> getLinkedDrawablesForAnimation(){
  List<Drawable> drawables=null;
  for (int i=0, size=(mDrawableMountItems == null) ? 0 : mDrawableMountItems.size(); i < size; i++) {
    final MountItem mountItem=mDrawableMountItems.valueAt(i);
    if ((mountItem.getLayoutFlags() & MountItem.LAYOUT_FLAG_MATCH_HOST_BOUNDS) != 0) {
      if (drawables == null) {
        drawables=new ArrayList<>();
      }
      drawables.add((Drawable)mountItem.getContent());
    }
  }
  return drawables;
}

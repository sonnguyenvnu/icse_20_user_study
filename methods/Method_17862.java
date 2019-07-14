/** 
 * @return list of drawables that are mounted on this host.
 */
public List<Drawable> getDrawables(){
  if (mDrawableMountItems == null || mDrawableMountItems.size() == 0) {
    return Collections.emptyList();
  }
  final List<Drawable> drawables=new ArrayList<>(mDrawableMountItems.size());
  for (int i=0, size=mDrawableMountItems.size(); i < size; i++) {
    Drawable drawable=(Drawable)mDrawableMountItems.valueAt(i).getContent();
    drawables.add(drawable);
  }
  return drawables;
}

/** 
 * @return a list of view's visibility, iterating from given view to its ancestor views. 
 */
private static List<String> getVisibleHierarchy(View view){
  final List<String> hierarchy=new ArrayList<>();
  Object current=view;
  while (current instanceof View) {
    final View currentView=(View)current;
    hierarchy.add("view=" + currentView.getClass().getSimpleName() + ", alpha=" + currentView.getAlpha() + ", visibility=" + currentView.getVisibility());
    if (currentView.getAlpha() <= 0 || currentView.getVisibility() != View.VISIBLE) {
      break;
    }
    current=currentView.getParent();
  }
  return hierarchy;
}

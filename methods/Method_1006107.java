/** 
 * Makes sure that the given component is visible.
 */
public void show(SidePaneType type){
  SidePaneComponent component=getComponent(type);
  if (!visibleComponents.contains(component)) {
    visibleComponents.add(component);
    visibleComponents.sort(new PreferredIndexSort());
    updateView();
    component.afterOpening();
  }
}

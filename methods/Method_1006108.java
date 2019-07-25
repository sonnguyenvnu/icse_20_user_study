/** 
 * Makes sure that the given component is not visible.
 */
public void hide(SidePaneType type){
  SidePaneComponent component=getComponent(type);
  if (visibleComponents.contains(component)) {
    component.beforeClosing();
    visibleComponents.remove(component);
    updateView();
  }
}

/** 
 * Selects the Navigation Item in Nav Drawer.
 * @param navItem The Nav Item to select.
 */
public void selectNavItem(@NavigationItem int navItem){
  selectItem(getViewForSelection(navItem));
}

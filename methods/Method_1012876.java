/** 
 * Inflate a menu resource into this SpeedDialView. Any existing Action item will be removed. <p class="note">Using the Menu resource it is possible to specify only the ID, the icon and the label of the Action item. No color customization is available.</p>
 * @param menuRes Menu resource to inflate
 */
public void inflate(@MenuRes int menuRes){
  clearActionItems();
  PopupMenu popupMenu=new PopupMenu(getContext(),new View(getContext()));
  popupMenu.inflate(menuRes);
  Menu menu=popupMenu.getMenu();
  for (int i=0; i < menu.size(); i++) {
    MenuItem menuItem=menu.getItem(i);
    SpeedDialActionItem actionItem=new SpeedDialActionItem.Builder(menuItem.getItemId(),menuItem.getIcon()).setLabel(menuItem.getTitle() != null ? menuItem.getTitle().toString() : null).create();
    addActionItem(actionItem);
  }
}

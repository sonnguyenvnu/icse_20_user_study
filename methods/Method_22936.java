/** 
 * Add a panel with a name and icon.
 * @param comp Component that will be shown when this tab is selected
 * @param name Title to appear on the tab itself
 * @param icon Prefix of the file name for the icon
 */
public void addPanel(Component comp,String name,String icon){
  tabs.add(new Tab(comp,name,icon));
  cardPanel.add(name,comp);
}

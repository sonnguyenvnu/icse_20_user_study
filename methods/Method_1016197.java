/** 
 * Installs menu item model change listener and returns it.
 * @param menuItem menu item to install listener into
 * @return installed model change listener
 */
public static MenuItemChangeListener install(final JMenuItem menuItem){
  final MenuItemChangeListener listener=new MenuItemChangeListener(menuItem);
  menuItem.getModel().addChangeListener(listener);
  menuItem.addPropertyChangeListener(AbstractButton.MODEL_CHANGED_PROPERTY,new PropertyChangeListener(){
    @Override public void propertyChange(    final PropertyChangeEvent evt){
      ((ButtonModel)evt.getOldValue()).removeChangeListener(listener);
      menuItem.getModel().addChangeListener(listener);
    }
  }
);
  return listener;
}

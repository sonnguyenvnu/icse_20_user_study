/** 
 * Installs rollover selection adapter into list and ensures that it is the only one installed.
 * @param list list to modify
 * @return installed rollover selection adapter
 */
public static ListRolloverSelectionAdapter install(final JList list){
  uninstall(list);
  final ListRolloverSelectionAdapter adapter=new ListRolloverSelectionAdapter(list);
  list.addMouseMotionListener(adapter);
  return adapter;
}

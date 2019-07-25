/** 
 * Installs rollover selection adapter into tree and ensures that it is the only one installed.
 * @param tree tree to modify
 * @return installed rollover selection adapter
 */
public static TreeRolloverSelectionAdapter install(final JTree tree){
  uninstall(tree);
  final TreeRolloverSelectionAdapter adapter=new TreeRolloverSelectionAdapter(tree);
  tree.addMouseMotionListener(adapter);
  return adapter;
}

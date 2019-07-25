/** 
 * Installs listener into tree and ensures that it is the only one installed.
 * @param tree tree to modify
 * @return installed listener
 */
public static AutoExpandSingleChildNodeListener install(final JTree tree){
  uninstall(tree);
  final AutoExpandSingleChildNodeListener adapter=new AutoExpandSingleChildNodeListener();
  tree.addTreeExpansionListener(adapter);
  return adapter;
}

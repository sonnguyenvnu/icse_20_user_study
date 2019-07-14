/** 
 * Generate a tree of  {@link ComponentLayout} representing the layout structure of the {@link Component} and its sub-components. You should use {@link ComponentContext#newLayoutBuilder} tobuild the layout tree.
 * @param c The {@link ComponentContext} to build a {@link ComponentLayout} tree.
 */
protected Component onCreateLayout(ComponentContext c){
  return Column.create(c).build();
}

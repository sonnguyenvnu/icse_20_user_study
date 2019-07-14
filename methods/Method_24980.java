/** 
 * Update declared (non-inherited) this-fields data.
 * @param nodes a list of {@link VariableNode}s to be shown as declared this-fields in the inspector.
 * @param title the title to be used when labeling or otherwise groupingdeclared this-fields data.
 */
public void updateDeclaredThisFields(List<VariableNode> nodes,String title){
  declaredThisFields=nodes;
}

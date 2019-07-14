/** 
 * Removes duplicate names from bean references.
 */
private void removeAllDuplicateNames(final BeanReferences[] allBeanReferences){
  for (int i=0; i < allBeanReferences.length; i++) {
    BeanReferences references=allBeanReferences[i];
    allBeanReferences[i]=references.removeDuplicateNames();
  }
}

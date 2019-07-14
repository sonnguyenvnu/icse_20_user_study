/** 
 * Updates existing generated Component file.
 * @param qualifiedSpecName fully qualified name of the Spec class to update Component for.
 * @param specModel {@link SpecModel} of the Spec class
 */
private static void updateComponent(Project project,String qualifiedSpecName,SpecModel specModel){
  new ComponentUpdater(project,specModel).tryCreate(qualifiedSpecName);
}

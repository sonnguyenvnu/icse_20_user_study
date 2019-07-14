/** 
 * Counts actual real hints.
 */
@Override public void init(final TemplateData templateData){
  super.init(templateData);
  if (hint != null) {
    templateData.incrementHintsCount();
  }
}

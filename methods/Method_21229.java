/** 
 * Gets the data object associated with a sectionRow.
 */
protected Object objectFromSectionRow(final @NonNull SectionRow sectionRow){
  return this.sections.get(sectionRow.section()).get(sectionRow.row());
}

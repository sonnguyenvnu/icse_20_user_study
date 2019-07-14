/** 
 * Finds entity descriptor of a table that contains provided column reference.
 */
public DbEntityDescriptor findTableDescriptorByColumnRef(final String columnRef){
  for (  Map.Entry<String,TableRefData> entry : tableRefs.entrySet()) {
    DbEntityDescriptor ded=entry.getValue().desc;
    if (ded.findByPropertyName(columnRef) != null) {
      return ded;
    }
  }
  return null;
}

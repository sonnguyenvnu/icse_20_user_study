/** 
 * <p>Puts a  {@link CustomProperty} that has not yet a valid ID into thismap. The method will allocate a suitable ID for the custom property:</p> <ul> <li><p>If there is already a property with the same name, take the ID of that property.</p></li> <li><p>Otherwise find the highest ID and use its value plus one.</p></li> </ul>
 * @param customProperty
 * @return If the was already a property with the same name, the
 * @throws ClassCastException
 */
private Object put(final CustomProperty customProperty) throws ClassCastException {
  final String name=customProperty.getName();
  final Long oldId=dictionaryNameToID.get(name);
  if (oldId != null)   customProperty.setID(oldId.longValue());
 else {
    long max=1;
    for (final Iterator<Long> i=dictionaryIDToName.keySet().iterator(); i.hasNext(); ) {
      final long id=i.next().longValue();
      if (id > max)       max=id;
    }
    customProperty.setID(max + 1);
  }
  return this.put(name,customProperty);
}

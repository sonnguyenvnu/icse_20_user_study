/** 
 * Loads base properties from the provided java properties. Null values are ignored.
 */
public Props load(final Map<?,?> p){
  for (  final Map.Entry<?,?> entry : p.entrySet()) {
    final String name=entry.getKey().toString();
    final Object value=entry.getValue();
    if (value == null) {
      continue;
    }
    data.putBaseProperty(name,value.toString(),false);
  }
  return this;
}

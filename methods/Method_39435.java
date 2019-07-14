/** 
 * Loads base properties from java Map using provided prefix. Null values are ignored.
 */
@SuppressWarnings("unchecked") public Props load(final Map<?,?> map,final String prefix){
  String realPrefix=prefix;
  realPrefix+='.';
  for (  final Map.Entry entry : map.entrySet()) {
    final String name=entry.getKey().toString();
    final Object value=entry.getValue();
    if (value == null) {
      continue;
    }
    data.putBaseProperty(realPrefix + name,value.toString(),false);
  }
  return this;
}

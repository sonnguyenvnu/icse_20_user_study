/** 
 * Defines  {@link jodd.json.ValueConverter} to use on given path.
 */
public JsonParser withValueConverter(final String path,final ValueConverter valueConverter){
  if (convs == null) {
    convs=new HashMap<>();
  }
  convs.put(Path.parse(path),valueConverter);
  return this;
}

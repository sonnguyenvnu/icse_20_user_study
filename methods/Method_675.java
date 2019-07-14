/** 
 * add class level serialize filter
 * @since 1.2.10
 */
public void addFilter(Class<?> clazz,SerializeFilter filter){
  ObjectSerializer serializer=getObjectWriter(clazz);
  if (serializer instanceof SerializeFilterable) {
    SerializeFilterable filterable=(SerializeFilterable)serializer;
    if (this != SerializeConfig.globalInstance) {
      if (filterable == MapSerializer.instance) {
        MapSerializer newMapSer=new MapSerializer();
        this.put(clazz,newMapSer);
        newMapSer.addFilter(filter);
        return;
      }
    }
    filterable.addFilter(filter);
  }
}

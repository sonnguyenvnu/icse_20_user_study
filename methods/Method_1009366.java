/** 
 * Maps  {@code source} to {@code destination}. Mapping is performed according to the corresponding TypeMap. If no TypeMap exists for  {@code source.getClass()} and {@code destination.getClass()}then one is created.
 * @param source object to map from
 * @param destination object to map to
 * @throws IllegalArgumentException if {@code source} or {@code destination} are null
 * @throws ConfigurationException if the ModelMapper cannot find or create a TypeMap for thearguments
 * @throws MappingException if an error occurs while mapping
 */
public void map(Object source,Object destination){
  Assert.notNull(source,"source");
  Assert.notNull(destination,"destination");
  mapInternal(source,destination,null,null);
}

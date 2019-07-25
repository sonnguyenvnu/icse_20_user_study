/** 
 * Defines a mapping from the  {@code source} to the {@code destination}. See the See the <a href="#0">EDSL examples</a>.
 * @param source to map from
 * @param destination to map to
 * @throws IllegalStateException if called from outside the context of{@link PropertyMap#configure()}.
 */
protected final void map(Object source,Object destination){
  assertBuilder();
  builder.map(source,destination);
}

/** 
 * Defines a mapping for the  {@code subject}. See the See the <a href="#0">EDSL examples</a>.
 * @param subject to map
 * @throws IllegalStateException if called from outside the context of{@link PropertyMap#configure()}.
 */
protected final D map(Object subject){
  assertBuilder();
  return builder.map(subject);
}

/** 
 * Specifies that mapping for the destination property be skipped during the mapping process. See the <a href="#3">EDSL examples</a>.
 * @throws IllegalStateException if called from outside the context of{@link PropertyMap#configure()}.
 */
protected final D skip(){
  assertBuilder();
  return builder.skip();
}

/** 
 * Creates a child MappingContext for an element of a destination collection. 
 */
@Override public <CS,CD>MappingContext<CS,CD> create(CS source,Type destinationType){
  Assert.notNull(source,"source");
  Assert.notNull(destinationType,"destinationType");
  TypeToken<CD> destinationTypeToken=TypeToken.of(destinationType);
  return new MappingContextImpl<CS,CD>(this,source,Types.<CS>deProxy(source.getClass()),null,destinationTypeToken.getRawType(),destinationTypeToken.getType(),mapping,false);
}

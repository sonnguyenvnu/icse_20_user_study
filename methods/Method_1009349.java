/** 
 * Creates a child MappingContext for an element of a destination collection. 
 */
@Override public <CS,CD>MappingContext<CS,CD> create(CS source,Class<CD> destinationType){
  Assert.notNull(source,"source");
  Assert.notNull(destinationType,"destinationType");
  return new MappingContextImpl<CS,CD>(this,source,Types.<CS>deProxy(source.getClass()),null,destinationType,null,null,false);
}

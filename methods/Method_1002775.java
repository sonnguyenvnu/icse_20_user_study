/** 
 * Returns all  {@link Attribute}s this map contains.
 */
public Iterator<Attribute<?>> attrs(){
  final AtomicReferenceArray<DefaultAttribute<?>> attributes=this.attributes;
  if (attributes == null) {
    return Collections.emptyIterator();
  }
  return new IteratorImpl(attributes);
}

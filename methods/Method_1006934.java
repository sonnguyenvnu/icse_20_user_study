/** 
 * @see org.springframework.batch.item.file.transform.FieldExtractor#extract(java.lang.Object)
 */
@Override public Object[] extract(T item){
  List<Object> values=new ArrayList<>();
  BeanWrapper bw=new BeanWrapperImpl(item);
  for (  String propertyName : this.names) {
    values.add(bw.getPropertyValue(propertyName));
  }
  return values.toArray();
}

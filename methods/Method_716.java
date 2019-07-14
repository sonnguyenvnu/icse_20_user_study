/** 
 * Add serialize filter.
 * @param filter the filter
 * @see FastJsonConfig#setSerializeFilters(SerializeFilter)
 * @deprecated
 */
@Deprecated public void addSerializeFilter(SerializeFilter filter){
  if (filter == null) {
    return;
  }
  int length=this.fastJsonConfig.getSerializeFilters().length;
  SerializeFilter[] filters=new SerializeFilter[length + 1];
  System.arraycopy(this.fastJsonConfig.getSerializeFilters(),0,filters,0,length);
  filters[filters.length - 1]=filter;
  this.fastJsonConfig.setSerializeFilters(filters);
}

/** 
 * Sets new bean instance.
 */
private void setBean(final Object bean){
  this.bean=bean;
  this.cd=(bean == null ? null : introspector.lookup(bean.getClass()));
  this.first=false;
  this.updateProperty=true;
}

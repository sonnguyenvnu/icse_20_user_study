/** 
 * Updates the bean. Detects special case of suppliers.
 */
public void updateBean(final Object bean){
  this.setBean(bean);
  if (this.cd != null && this.cd.isSupplier()) {
    final Object newBean=((Supplier)this.bean).get();
    setBean(newBean);
  }
}

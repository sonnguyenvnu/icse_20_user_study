/** 
 * Checks if bean data is destroyable (has destroy methods) and registers it for later  {@link #shutdown()}.
 */
protected void registerDestroyableBeans(final BeanData beanData){
  if (!isBeanDestroyable(beanData)) {
    return;
  }
  if (destroyableBeans == null) {
    destroyableBeans=new ArrayList<>();
  }
  destroyableBeans.add(beanData);
}

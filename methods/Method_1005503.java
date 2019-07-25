/** 
 * {@inheritDoc}
 * @deprecated DozerModule needs rethink, as exposes internals
 */
@Deprecated @Override public void init(BeanContainer beanContainer,DestBeanCreator destBeanCreator,PropertyDescriptorFactory propertyDescriptorFactory){
  this.beanContainer=beanContainer;
  this.destBeanCreator=destBeanCreator;
  this.propertyDescriptorFactory=propertyDescriptorFactory;
}

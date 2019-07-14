/** 
 * Wires provided bean with the container and optionally invokes init methods. Bean is <b>not</b> registered withing container.
 */
public void wire(final Object bean,final WiringMode wiringMode){
  final WiringMode finalWiringMode=petiteConfig.resolveWiringMode(wiringMode);
  final BeanDefinition def=externalsCache.get(bean.getClass(),() -> {
    final BeanDefinition beanDefinition=createBeandDefinitionForExternalBeans(bean.getClass(),finalWiringMode);
    initBeanDefinition(beanDefinition);
    return beanDefinition;
  }
);
  registerBeanAndWireAndInjectParamsAndInvokeInitMethods(new BeanData(this,def,bean));
}

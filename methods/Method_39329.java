/** 
 * Creates a new instance.
 */
public Object newBeanInstance(){
  if (beanDefinition.ctor == CtorInjectionPoint.EMPTY) {
    throw new PetiteException("No constructor (annotated, single or default) founded as injection point for: " + beanDefinition.type.getName());
  }
  int paramNo=beanDefinition.ctor.references.length;
  Object[] args=new Object[paramNo];
  if (beanDefinition.wiringMode != WiringMode.NONE) {
    for (int i=0; i < paramNo; i++) {
      args[i]=pc.getBean(beanDefinition.ctor.references[i]);
      if (args[i] == null) {
        if ((beanDefinition.wiringMode == WiringMode.STRICT)) {
          throw new PetiteException("Wiring constructor failed. References '" + beanDefinition.ctor.references[i] + "' not found for constructor: " + beanDefinition.ctor.constructor);
        }
      }
    }
  }
  final Object bean;
  try {
    bean=beanDefinition.ctor.constructor.newInstance(args);
  }
 catch (  Exception ex) {
    throw new PetiteException("Failed to create new bean instance '" + beanDefinition.type.getName() + "' using constructor: " + beanDefinition.ctor.constructor,ex);
  }
  return bean;
}

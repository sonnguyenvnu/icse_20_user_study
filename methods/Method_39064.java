/** 
 * Resolves the name of the last base non-abstract subclass for provided component. It iterates all subclasses up to the <code>Object</cde> and declares the last non-abstract class as base component. Component name will be resolved from the founded base component.
 */
private String resolveBaseComponentName(Class component){
  Class lastComponent=component;
  while (true) {
    Class superClass=component.getSuperclass();
    if (superClass.equals(Object.class)) {
      break;
    }
    component=superClass;
    if (!Modifier.isAbstract(component.getModifiers())) {
      lastComponent=component;
    }
  }
  return madpc.resolveBeanName(lastComponent);
}

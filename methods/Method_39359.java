/** 
 * Removes all petite beans of provided type. Bean name is not resolved from a type! Instead, all beans are iterated and only beans with equal types are removed.
 * @see #removeBean(String)
 */
public void removeBean(final Class type){
  Set<String> beanNames=new HashSet<>();
  for (  BeanDefinition def : beans.values()) {
    if (def.type.equals(type)) {
      beanNames.add(def.name);
    }
  }
  for (  String beanName : beanNames) {
    removeBean(beanName);
  }
}

/** 
 * Iterates all beans that are of given type.
 */
public void forEachBeanType(final Class type,final Consumer<String> beanNameConsumer){
  forEachBean(bd -> {
    if (ClassUtil.isTypeOf(bd.type,type)) {
      beanNameConsumer.accept(bd.name);
    }
  }
);
}

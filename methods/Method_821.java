public static boolean isAnnotationPresentManyToMany(Method method){
  if (method == null) {
    return false;
  }
  if (class_ManyToMany == null && !class_ManyToMany_error) {
    try {
      class_ManyToMany=(Class<? extends Annotation>)Class.forName("javax.persistence.ManyToMany");
    }
 catch (    Throwable e) {
      class_ManyToMany_error=true;
    }
  }
  return class_ManyToMany != null && (method.isAnnotationPresent(class_OneToMany) || method.isAnnotationPresent(class_ManyToMany));
}

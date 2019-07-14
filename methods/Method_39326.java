/** 
 * Registers a class consumer that registers only those annotated with  {@link jodd.petite.meta.PetiteBean}. Because of performance purposes, classes are not dynamically loaded; instead, their file content is examined.
 */
public void registerAsConsumer(final ClassScanner classScanner){
  classScanner.registerEntryConsumer(classPathEntry -> {
    if (!classPathEntry.isTypeSignatureInUse(PETITE_BEAN_ANNOTATION_BYTES)) {
      return;
    }
    final Class<?> beanClass;
    try {
      beanClass=classPathEntry.loadClass();
    }
 catch (    ClassNotFoundException cnfex) {
      throw new PetiteException("Unable to load class: " + cnfex,cnfex);
    }
    if (beanClass == null) {
      return;
    }
    final PetiteBean petiteBean=beanClass.getAnnotation(PetiteBean.class);
    if (petiteBean == null) {
      return;
    }
    container.registerPetiteBean(beanClass,null,null,null,false,null);
  }
);
}

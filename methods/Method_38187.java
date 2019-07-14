/** 
 * Registers a class consumer that registers only those annotated with  {@link DbTable}. Because of performance purposes, classes are not dynamically loaded; instead, their file content is examined.
 */
public void registerAsConsumer(final ClassScanner classScanner){
  classScanner.registerEntryConsumer(classPathEntry -> {
    if (!classPathEntry.isTypeSignatureInUse(DB_TABLE_ANNOTATION_BYTES)) {
      return;
    }
    final Class<?> beanClass;
    try {
      beanClass=classPathEntry.loadClass();
    }
 catch (    ClassNotFoundException cnfex) {
      throw new DbOomException("Entry class not found: " + classPathEntry.name(),cnfex);
    }
    if (beanClass == null) {
      return;
    }
    final DbTable dbTable=beanClass.getAnnotation(DbTable.class);
    if (dbTable == null) {
      return;
    }
    if (registerAsEntities) {
      dbEntityManager.registerEntity(beanClass);
    }
 else {
      dbEntityManager.registerType(beanClass);
    }
  }
);
}

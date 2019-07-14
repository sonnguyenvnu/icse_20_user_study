/** 
 * Parses class name that matches madvoc-related names.
 */
protected void registerAsConsumer(final ClassScanner classScanner){
  classScanner.registerEntryConsumer(classPathEntry -> {
    final String entryName=classPathEntry.name();
    if (entryName.endsWith(actionClassSuffix)) {
      try {
        acceptActionClass(classPathEntry.loadClass());
      }
 catch (      Exception ex) {
        log.debug("Invalid Madvoc action, ignoring: " + entryName);
      }
    }
 else     if (classPathEntry.isTypeSignatureInUse(MADVOC_COMPONENT_ANNOTATION)) {
      try {
        acceptMadvocComponentClass(classPathEntry.loadClass());
      }
 catch (      Exception ex) {
        log.debug("Invalid Madvoc component ignoring: {}" + entryName);
      }
    }
  }
);
}

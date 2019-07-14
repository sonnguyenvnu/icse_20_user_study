/** 
 * If entry name is  {@link #acceptEntry(String) accepted} invokes {@link #onEntry(ClassPathEntry)} a callback}.
 */
protected void scanEntry(final ClassPathEntry classPathEntry){
  if (!acceptEntry(classPathEntry.name())) {
    return;
  }
  try {
    onEntry(classPathEntry);
  }
 catch (  Exception ex) {
    throw new FindFileException("Scan entry error: " + classPathEntry,ex);
  }
}

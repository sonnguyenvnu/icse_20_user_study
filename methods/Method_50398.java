/** 
 * Gets extension loader.
 * @param < T >  the type parameter
 * @param type the type
 * @return the extension loader
 */
public static <T>ExtensionLoader<T> getExtensionLoader(final Class<T> type){
  if (type == null) {
    throw new HmilyException("type == null");
  }
  if (!type.isInterface()) {
    throw new HmilyException("Extension type(" + type + ") not interface!");
  }
  if (!withExtensionAnnotation(type)) {
    throw new HmilyException("type" + type.getName() + "not exist");
  }
  return new ExtensionLoader<>(type);
}

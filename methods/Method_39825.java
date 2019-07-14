/** 
 * Returns target class if proxetta applied on given class. If not, returns given class as result.
 */
public static Class resolveTargetClass(final Class proxy){
  final String name=proxy.getName();
  if (name.endsWith(ProxettaNames.proxyClassNameSuffix)) {
    return proxy.getSuperclass();
  }
  if (name.endsWith(ProxettaNames.wrapperClassNameSuffix)) {
    return getTargetWrapperType(proxy);
  }
  return proxy;
}

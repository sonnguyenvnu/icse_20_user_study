/** 
 * Calls static "provideAliases" method on a class that implements AliasProvider. This method is created mostly for internal library usage.
 * @param aliasProvider AliasProvider ancestor class
 * @param < T >           specific class type
 */
public static <T extends AliasProvider>void alias(final Class<T> aliasProvider){
  ReflectUtils.callStaticMethodSafely(aliasProvider,AliasProvider.methodName,getXStream());
}

@Init @SuppressWarnings({"rawtypes","unchecked"}) public void init(PluginConfiguration pluginConfiguration,ClassLoader appClassLoader){
  try {
    Class kvcDefaultImplementationClass=Class.forName("com.webobjects.foundation.NSKeyValueCoding$DefaultImplementation",false,appClassLoader);
    kvcDefaultImplementation_flushCaches=kvcDefaultImplementationClass.getMethod("_flushCaches");
    Class kvcReflectionKeyBindingCreationClass=Class.forName("com.webobjects.foundation.NSKeyValueCoding$_ReflectionKeyBindingCreation",false,appClassLoader);
    kvcReflectionKeyBindingCreation_flushCaches=kvcReflectionKeyBindingCreationClass.getMethod("_flushCaches");
    Class kvcValueAccessorClass=Class.forName("com.webobjects.foundation.NSKeyValueCoding$ValueAccessor",false,appClassLoader);
    kvcValueAccessor_flushCaches=kvcValueAccessorClass.getMethod("_flushCaches");
    Class nsValidationDefaultImplementationClass=Class.forName("com.webobjects.foundation.NSValidation$DefaultImplementation",false,appClassLoader);
    nsValidationDefaultImplementation_flushCaches=nsValidationDefaultImplementationClass.getMethod("_flushCaches");
    Class woApplicationClass=Class.forName("com.webobjects.appserver.WOApplication",false,appClassLoader);
    woApplication_removeComponentDefinitionCacheContents=woApplicationClass.getMethod("_removeComponentDefinitionCacheContents");
    woApplicationObject=woApplicationClass.getMethod("application").invoke(null);
    ClassPool classPool=ClassPool.getDefault();
    woComponentCtClass=classPool.makeClass("com.webobjects.appserver.WOComponent");
    nsValidationCtClass=classPool.makeClass("com.webobjects.foundation.NSValidation");
    woActionCtClass=classPool.makeClass("com.webobjects.appserver.WOAction");
    Class woActionClass=Class.forName("com.webobjects.appserver.WOAction",false,appClassLoader);
    Field actionClassesField=woActionClass.getDeclaredField("_actionClasses");
    actionClassesField.setAccessible(true);
    actionClassesCacheDictionnary=actionClassesField.get(null);
    Class nsThreadsafeMutableDictionaryClass=Class.forName("com.webobjects.foundation._NSThreadsafeMutableDictionary",false,appClassLoader);
    woApplication_removeComponentDefinitionCacheContents=woApplicationClass.getMethod("_removeComponentDefinitionCacheContents");
    nsThreadsafeMutableDictionary_removeAllObjects=nsThreadsafeMutableDictionaryClass.getMethod("removeAllObjects");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}

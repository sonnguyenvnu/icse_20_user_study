/** 
 * Generates bytecode for the proxy class
 * @return bytecode of the new proxy class
 * @throws Exception
 */
@Override public byte[] generate() throws Exception {
  Collection<FieldState> oldClassValues=getFieldValuesWithClasses();
  ClassLoader oldClassLoader=(ClassLoader)ReflectionHelper.get(generator,"classLoader");
  Boolean oldUseCache=(Boolean)ReflectionHelper.get(generator,"useCache");
  try {
    ReflectionHelper.set(generator,abstractGeneratorClass,"classLoader",classLoader);
    ReflectionHelper.set(generator,abstractGeneratorClass,"useCache",Boolean.FALSE);
    setFieldValuesWithNewLoadedClasses(oldClassValues);
    byte[] invoke=(byte[])ReflectionHelper.invoke(param.getGenerator(),param.getGenerator().getClass(),"generate",new Class[]{getGeneratorInterfaceClass()},generator);
    return invoke;
  }
  finally {
    ReflectionHelper.set(generator,abstractGeneratorClass,"classLoader",oldClassLoader);
    ReflectionHelper.set(generator,abstractGeneratorClass,"useCache",oldUseCache);
    setFieldValues(oldClassValues);
  }
}

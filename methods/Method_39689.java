/** 
 * Resolves method parameters from a method or constructor. Returns an empty array when target does not contain any parameter. No caching is involved in this process, i.e. class bytecode is examined every time this method is called.
 */
public static MethodParameter[] resolveParameters(final AccessibleObject methodOrCtor){
  final Class[] paramTypes;
  final Parameter[] parameters;
  final Class declaringClass;
  final String name;
  if (methodOrCtor instanceof Method) {
    final Method method=(Method)methodOrCtor;
    paramTypes=method.getParameterTypes();
    name=method.getName();
    declaringClass=method.getDeclaringClass();
    parameters=method.getParameters();
  }
 else {
    final Constructor constructor=(Constructor)methodOrCtor;
    paramTypes=constructor.getParameterTypes();
    declaringClass=constructor.getDeclaringClass();
    name=CTOR_METHOD;
    parameters=constructor.getParameters();
  }
  if (paramTypes.length == 0) {
    return MethodParameter.EMPTY_ARRAY;
  }
  final InputStream stream;
  try {
    stream=ClassLoaderUtil.getClassAsStream(declaringClass);
  }
 catch (  final IOException ioex) {
    throw new ParamoException("Failed to read class bytes: " + declaringClass.getName(),ioex);
  }
  if (stream == null) {
    throw new ParamoException("Class not found: " + declaringClass);
  }
  try {
    final ClassReader reader=new ClassReader(stream);
    final MethodFinder visitor=new MethodFinder(declaringClass,name,paramTypes,parameters);
    reader.accept(visitor,0);
    return visitor.getResolvedParameters();
  }
 catch (  final IOException ioex) {
    throw new ParamoException(ioex);
  }
 finally {
    StreamUtil.close(stream);
  }
}

/** 
 * Inspects types methods and return map of  {@link MethodDescriptor method descriptors}.
 */
protected HashMap<String,MethodDescriptor[]> inspectMethods(){
  boolean scanAccessible=classDescriptor.isScanAccessible();
  if (classDescriptor.isSystemClass()) {
    scanAccessible=false;
  }
  final Class type=classDescriptor.getType();
  final Method[] methods=scanAccessible ? ClassUtil.getAccessibleMethods(type) : ClassUtil.getSupportedMethods(type);
  final HashMap<String,MethodDescriptor[]> map=new HashMap<>(methods.length);
  for (  final Method method : methods) {
    final String methodName=method.getName();
    MethodDescriptor[] mds=map.get(methodName);
    if (mds == null) {
      mds=new MethodDescriptor[1];
    }
 else {
      mds=ArraysUtil.resize(mds,mds.length + 1);
    }
    map.put(methodName,mds);
    mds[mds.length - 1]=createMethodDescriptor(method);
  }
  return map;
}

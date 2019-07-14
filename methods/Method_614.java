public Class<?> checkAutoType(String typeName,Class<?> expectClass,int features){
  if (typeName == null) {
    return null;
  }
  if (typeName.length() >= 192 || typeName.length() < 3) {
    throw new JSONException("autoType is not support. " + typeName);
  }
  final boolean expectClassFlag;
  if (expectClass == null) {
    expectClassFlag=false;
  }
 else {
    if (expectClass == Object.class || expectClass == Serializable.class || expectClass == Cloneable.class || expectClass == Closeable.class || expectClass == EventListener.class || expectClass == Iterable.class || expectClass == Collection.class) {
      expectClassFlag=false;
    }
 else {
      expectClassFlag=true;
    }
  }
  String className=typeName.replace('$','.');
  Class<?> clazz=null;
  final long BASIC=0xcbf29ce484222325L;
  final long PRIME=0x100000001b3L;
  final long h1=(BASIC ^ className.charAt(0)) * PRIME;
  if (h1 == 0xaf64164c86024f1aL) {
    throw new JSONException("autoType is not support. " + typeName);
  }
  if ((h1 ^ className.charAt(className.length() - 1)) * PRIME == 0x9198507b5af98f0L) {
    throw new JSONException("autoType is not support. " + typeName);
  }
  final long h3=(((((BASIC ^ className.charAt(0)) * PRIME) ^ className.charAt(1)) * PRIME) ^ className.charAt(2)) * PRIME;
  if (autoTypeSupport || expectClassFlag) {
    long hash=h3;
    for (int i=3; i < className.length(); ++i) {
      hash^=className.charAt(i);
      hash*=PRIME;
      if (Arrays.binarySearch(acceptHashCodes,hash) >= 0) {
        clazz=TypeUtils.loadClass(typeName,defaultClassLoader,true);
        if (clazz != null) {
          return clazz;
        }
      }
      if (Arrays.binarySearch(denyHashCodes,hash) >= 0 && TypeUtils.getClassFromMapping(typeName) == null) {
        throw new JSONException("autoType is not support. " + typeName);
      }
    }
  }
  if (clazz == null) {
    clazz=TypeUtils.getClassFromMapping(typeName);
  }
  if (clazz == null) {
    clazz=deserializers.findClass(typeName);
  }
  if (clazz == null) {
    clazz=typeMapping.get(typeName);
  }
  if (clazz != null) {
    if (expectClass != null && clazz != java.util.HashMap.class && !expectClass.isAssignableFrom(clazz)) {
      throw new JSONException("type not match. " + typeName + " -> " + expectClass.getName());
    }
    return clazz;
  }
  if (!autoTypeSupport) {
    long hash=h3;
    for (int i=3; i < className.length(); ++i) {
      char c=className.charAt(i);
      hash^=c;
      hash*=PRIME;
      if (Arrays.binarySearch(denyHashCodes,hash) >= 0) {
        throw new JSONException("autoType is not support. " + typeName);
      }
      if (Arrays.binarySearch(acceptHashCodes,hash) >= 0) {
        if (clazz == null) {
          clazz=TypeUtils.loadClass(typeName,defaultClassLoader,true);
        }
        if (expectClass != null && expectClass.isAssignableFrom(clazz)) {
          throw new JSONException("type not match. " + typeName + " -> " + expectClass.getName());
        }
        return clazz;
      }
    }
  }
  boolean jsonType=false;
  InputStream is=null;
  try {
    String resource=typeName.replace('.','/') + ".class";
    if (defaultClassLoader != null) {
      is=defaultClassLoader.getResourceAsStream(resource);
    }
 else {
      is=ParserConfig.class.getClassLoader().getResourceAsStream(resource);
    }
    if (is != null) {
      ClassReader classReader=new ClassReader(is,true);
      TypeCollector visitor=new TypeCollector("<clinit>",new Class[0]);
      classReader.accept(visitor);
      jsonType=visitor.hasJsonType();
    }
  }
 catch (  Exception e) {
  }
 finally {
    IOUtils.close(is);
  }
  final int mask=Feature.SupportAutoType.mask;
  boolean autoTypeSupport=this.autoTypeSupport || (features & mask) != 0 || (JSON.DEFAULT_PARSER_FEATURE & mask) != 0;
  if (clazz == null && (autoTypeSupport || jsonType || expectClassFlag)) {
    boolean cacheClass=autoTypeSupport || jsonType;
    clazz=TypeUtils.loadClass(typeName,defaultClassLoader,cacheClass);
  }
  if (clazz != null) {
    if (jsonType) {
      TypeUtils.addMapping(typeName,clazz);
      return clazz;
    }
    if (ClassLoader.class.isAssignableFrom(clazz) || javax.sql.DataSource.class.isAssignableFrom(clazz) || javax.sql.RowSet.class.isAssignableFrom(clazz)) {
      throw new JSONException("autoType is not support. " + typeName);
    }
    if (expectClass != null) {
      if (expectClass.isAssignableFrom(clazz)) {
        TypeUtils.addMapping(typeName,clazz);
        return clazz;
      }
 else {
        throw new JSONException("type not match. " + typeName + " -> " + expectClass.getName());
      }
    }
    JavaBeanInfo beanInfo=JavaBeanInfo.build(clazz,clazz,propertyNamingStrategy);
    if (beanInfo.creatorConstructor != null && autoTypeSupport) {
      throw new JSONException("autoType is not support. " + typeName);
    }
  }
  if (!autoTypeSupport) {
    throw new JSONException("autoType is not support. " + typeName);
  }
  if (clazz != null) {
    TypeUtils.addMapping(typeName,clazz);
  }
  return clazz;
}

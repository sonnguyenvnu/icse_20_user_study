public ObjectDeserializer getDeserializer(Class<?> clazz,Type type){
  ObjectDeserializer derializer=deserializers.get(type);
  if (derializer != null) {
    return derializer;
  }
  if (type == null) {
    type=clazz;
  }
  derializer=deserializers.get(type);
  if (derializer != null) {
    return derializer;
  }
{
    JSONType annotation=TypeUtils.getAnnotation(clazz,JSONType.class);
    if (annotation != null) {
      Class<?> mappingTo=annotation.mappingTo();
      if (mappingTo != Void.class) {
        return getDeserializer(mappingTo,mappingTo);
      }
    }
  }
  if (type instanceof WildcardType || type instanceof TypeVariable || type instanceof ParameterizedType) {
    derializer=deserializers.get(clazz);
  }
  if (derializer != null) {
    return derializer;
  }
  for (  Module module : modules) {
    derializer=module.createDeserializer(this,clazz);
    if (derializer != null) {
      putDeserializer(type,derializer);
      return derializer;
    }
  }
  String className=clazz.getName();
  className=className.replace('$','.');
  if (className.startsWith("java.awt.") && AwtCodec.support(clazz)) {
    if (!awtError) {
      String[] names=new String[]{"java.awt.Point","java.awt.Font","java.awt.Rectangle","java.awt.Color"};
      try {
        for (        String name : names) {
          if (name.equals(className)) {
            deserializers.put(Class.forName(name),derializer=AwtCodec.instance);
            return derializer;
          }
        }
      }
 catch (      Throwable e) {
        awtError=true;
      }
      derializer=AwtCodec.instance;
    }
  }
  if (!jdk8Error) {
    try {
      if (className.startsWith("java.time.")) {
        String[] names=new String[]{"java.time.LocalDateTime","java.time.LocalDate","java.time.LocalTime","java.time.ZonedDateTime","java.time.OffsetDateTime","java.time.OffsetTime","java.time.ZoneOffset","java.time.ZoneRegion","java.time.ZoneId","java.time.Period","java.time.Duration","java.time.Instant"};
        for (        String name : names) {
          if (name.equals(className)) {
            deserializers.put(Class.forName(name),derializer=Jdk8DateCodec.instance);
            return derializer;
          }
        }
      }
 else       if (className.startsWith("java.util.Optional")) {
        String[] names=new String[]{"java.util.Optional","java.util.OptionalDouble","java.util.OptionalInt","java.util.OptionalLong"};
        for (        String name : names) {
          if (name.equals(className)) {
            deserializers.put(Class.forName(name),derializer=OptionalCodec.instance);
            return derializer;
          }
        }
      }
    }
 catch (    Throwable e) {
      jdk8Error=true;
    }
  }
  if (!jodaError) {
    try {
      if (className.startsWith("org.joda.time.")) {
        String[] names=new String[]{"org.joda.time.DateTime","org.joda.time.LocalDate","org.joda.time.LocalDateTime","org.joda.time.LocalTime","org.joda.time.Instant","org.joda.time.Period","org.joda.time.Duration","org.joda.time.DateTimeZone","org.joda.time.format.DateTimeFormatter"};
        for (        String name : names) {
          if (name.equals(className)) {
            deserializers.put(Class.forName(name),derializer=JodaCodec.instance);
            return derializer;
          }
        }
      }
    }
 catch (    Throwable e) {
      jodaError=true;
    }
  }
  if ((!guavaError) && className.startsWith("com.google.common.collect.")) {
    try {
      String[] names=new String[]{"com.google.common.collect.HashMultimap","com.google.common.collect.LinkedListMultimap","com.google.common.collect.LinkedHashMultimap","com.google.common.collect.ArrayListMultimap","com.google.common.collect.TreeMultimap"};
      for (      String name : names) {
        if (name.equals(className)) {
          deserializers.put(Class.forName(name),derializer=GuavaCodec.instance);
          return derializer;
        }
      }
    }
 catch (    ClassNotFoundException e) {
      guavaError=true;
    }
  }
  if (className.equals("java.nio.ByteBuffer")) {
    deserializers.put(clazz,derializer=ByteBufferCodec.instance);
  }
  if (className.equals("java.nio.file.Path")) {
    deserializers.put(clazz,derializer=MiscCodec.instance);
  }
  if (clazz == Map.Entry.class) {
    deserializers.put(clazz,derializer=MiscCodec.instance);
  }
  if (className.equals("org.javamoney.moneta.Money")) {
    deserializers.put(clazz,derializer=MonetaCodec.instance);
  }
  final ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
  try {
    for (    AutowiredObjectDeserializer autowired : ServiceLoader.load(AutowiredObjectDeserializer.class,classLoader)) {
      for (      Type forType : autowired.getAutowiredFor()) {
        deserializers.put(forType,autowired);
      }
    }
  }
 catch (  Exception ex) {
  }
  if (derializer == null) {
    derializer=deserializers.get(type);
  }
  if (derializer != null) {
    return derializer;
  }
  if (clazz.isEnum()) {
    if (jacksonCompatible) {
      Method[] methods=clazz.getMethods();
      for (      Method method : methods) {
        if (TypeUtils.isJacksonCreator(method)) {
          derializer=createJavaBeanDeserializer(clazz,type);
          putDeserializer(type,derializer);
          return derializer;
        }
      }
    }
    Class<?> deserClass=null;
    JSONType jsonType=clazz.getAnnotation(JSONType.class);
    if (jsonType != null) {
      deserClass=jsonType.deserializer();
      try {
        derializer=(ObjectDeserializer)deserClass.newInstance();
        deserializers.put(clazz,derializer);
        return derializer;
      }
 catch (      Throwable error) {
      }
    }
    derializer=new EnumDeserializer(clazz);
  }
 else   if (clazz.isArray()) {
    derializer=ObjectArrayCodec.instance;
  }
 else   if (clazz == Set.class || clazz == HashSet.class || clazz == Collection.class || clazz == List.class || clazz == ArrayList.class) {
    derializer=CollectionCodec.instance;
  }
 else   if (Collection.class.isAssignableFrom(clazz)) {
    derializer=CollectionCodec.instance;
  }
 else   if (Map.class.isAssignableFrom(clazz)) {
    derializer=MapDeserializer.instance;
  }
 else   if (Throwable.class.isAssignableFrom(clazz)) {
    derializer=new ThrowableDeserializer(this,clazz);
  }
 else   if (PropertyProcessable.class.isAssignableFrom(clazz)) {
    derializer=new PropertyProcessableDeserializer((Class<PropertyProcessable>)clazz);
  }
 else   if (clazz == InetAddress.class) {
    derializer=MiscCodec.instance;
  }
 else {
    derializer=createJavaBeanDeserializer(clazz,type);
  }
  putDeserializer(type,derializer);
  return derializer;
}

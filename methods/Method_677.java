private ObjectSerializer getObjectWriter(Class<?> clazz,boolean create){
  ObjectSerializer writer=serializers.get(clazz);
  if (writer == null) {
    try {
      final ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
      for (      Object o : ServiceLoader.load(AutowiredObjectSerializer.class,classLoader)) {
        if (!(o instanceof AutowiredObjectSerializer)) {
          continue;
        }
        AutowiredObjectSerializer autowired=(AutowiredObjectSerializer)o;
        for (        Type forType : autowired.getAutowiredFor()) {
          put(forType,autowired);
        }
      }
    }
 catch (    ClassCastException ex) {
    }
    writer=serializers.get(clazz);
  }
  if (writer == null) {
    final ClassLoader classLoader=JSON.class.getClassLoader();
    if (classLoader != Thread.currentThread().getContextClassLoader()) {
      try {
        for (        Object o : ServiceLoader.load(AutowiredObjectSerializer.class,classLoader)) {
          if (!(o instanceof AutowiredObjectSerializer)) {
            continue;
          }
          AutowiredObjectSerializer autowired=(AutowiredObjectSerializer)o;
          for (          Type forType : autowired.getAutowiredFor()) {
            put(forType,autowired);
          }
        }
      }
 catch (      ClassCastException ex) {
      }
      writer=serializers.get(clazz);
    }
  }
  for (  Module module : modules) {
    writer=module.createSerializer(this,clazz);
    if (writer != null) {
      serializers.put(clazz,writer);
      return writer;
    }
  }
  if (writer == null) {
    String className=clazz.getName();
    Class<?> superClass;
    if (Map.class.isAssignableFrom(clazz)) {
      put(clazz,writer=MapSerializer.instance);
    }
 else     if (List.class.isAssignableFrom(clazz)) {
      put(clazz,writer=ListSerializer.instance);
    }
 else     if (Collection.class.isAssignableFrom(clazz)) {
      put(clazz,writer=CollectionCodec.instance);
    }
 else     if (Date.class.isAssignableFrom(clazz)) {
      put(clazz,writer=DateCodec.instance);
    }
 else     if (JSONAware.class.isAssignableFrom(clazz)) {
      put(clazz,writer=JSONAwareSerializer.instance);
    }
 else     if (JSONSerializable.class.isAssignableFrom(clazz)) {
      put(clazz,writer=JSONSerializableSerializer.instance);
    }
 else     if (JSONStreamAware.class.isAssignableFrom(clazz)) {
      put(clazz,writer=MiscCodec.instance);
    }
 else     if (clazz.isEnum()) {
      JSONType jsonType=TypeUtils.getAnnotation(clazz,JSONType.class);
      if (jsonType != null && jsonType.serializeEnumAsJavaBean()) {
        put(clazz,writer=createJavaBeanSerializer(clazz));
      }
 else {
        put(clazz,writer=EnumSerializer.instance);
      }
    }
 else     if ((superClass=clazz.getSuperclass()) != null && superClass.isEnum()) {
      JSONType jsonType=TypeUtils.getAnnotation(superClass,JSONType.class);
      if (jsonType != null && jsonType.serializeEnumAsJavaBean()) {
        put(clazz,writer=createJavaBeanSerializer(clazz));
      }
 else {
        put(clazz,writer=EnumSerializer.instance);
      }
    }
 else     if (clazz.isArray()) {
      Class<?> componentType=clazz.getComponentType();
      ObjectSerializer compObjectSerializer=getObjectWriter(componentType);
      put(clazz,writer=new ArraySerializer(componentType,compObjectSerializer));
    }
 else     if (Throwable.class.isAssignableFrom(clazz)) {
      SerializeBeanInfo beanInfo=TypeUtils.buildBeanInfo(clazz,null,propertyNamingStrategy);
      beanInfo.features|=SerializerFeature.WriteClassName.mask;
      put(clazz,writer=new JavaBeanSerializer(beanInfo));
    }
 else     if (TimeZone.class.isAssignableFrom(clazz) || Map.Entry.class.isAssignableFrom(clazz)) {
      put(clazz,writer=MiscCodec.instance);
    }
 else     if (Appendable.class.isAssignableFrom(clazz)) {
      put(clazz,writer=AppendableSerializer.instance);
    }
 else     if (Charset.class.isAssignableFrom(clazz)) {
      put(clazz,writer=ToStringSerializer.instance);
    }
 else     if (Enumeration.class.isAssignableFrom(clazz)) {
      put(clazz,writer=EnumerationSerializer.instance);
    }
 else     if (Calendar.class.isAssignableFrom(clazz) || XMLGregorianCalendar.class.isAssignableFrom(clazz)) {
      put(clazz,writer=CalendarCodec.instance);
    }
 else     if (TypeUtils.isClob(clazz)) {
      put(clazz,writer=ClobSeriliazer.instance);
    }
 else     if (TypeUtils.isPath(clazz)) {
      put(clazz,writer=ToStringSerializer.instance);
    }
 else     if (Iterator.class.isAssignableFrom(clazz)) {
      put(clazz,writer=MiscCodec.instance);
    }
 else     if (org.w3c.dom.Node.class.isAssignableFrom(clazz)) {
      put(clazz,writer=MiscCodec.instance);
    }
 else {
      if (className.startsWith("java.awt.") && AwtCodec.support(clazz)) {
        if (!awtError) {
          try {
            String[] names=new String[]{"java.awt.Color","java.awt.Font","java.awt.Point","java.awt.Rectangle"};
            for (            String name : names) {
              if (name.equals(className)) {
                put(Class.forName(name),writer=AwtCodec.instance);
                return writer;
              }
            }
          }
 catch (          Throwable e) {
            awtError=true;
          }
        }
      }
      if ((!jdk8Error) && (className.startsWith("java.time.") || className.startsWith("java.util.Optional") || className.equals("java.util.concurrent.atomic.LongAdder") || className.equals("java.util.concurrent.atomic.DoubleAdder"))) {
        try {
{
            String[] names=new String[]{"java.time.LocalDateTime","java.time.LocalDate","java.time.LocalTime","java.time.ZonedDateTime","java.time.OffsetDateTime","java.time.OffsetTime","java.time.ZoneOffset","java.time.ZoneRegion","java.time.Period","java.time.Duration","java.time.Instant"};
            for (            String name : names) {
              if (name.equals(className)) {
                put(Class.forName(name),writer=Jdk8DateCodec.instance);
                return writer;
              }
            }
          }
{
            String[] names=new String[]{"java.util.Optional","java.util.OptionalDouble","java.util.OptionalInt","java.util.OptionalLong"};
            for (            String name : names) {
              if (name.equals(className)) {
                put(Class.forName(name),writer=OptionalCodec.instance);
                return writer;
              }
            }
          }
{
            String[] names=new String[]{"java.util.concurrent.atomic.LongAdder","java.util.concurrent.atomic.DoubleAdder"};
            for (            String name : names) {
              if (name.equals(className)) {
                put(Class.forName(name),writer=AdderSerializer.instance);
                return writer;
              }
            }
          }
        }
 catch (        Throwable e) {
          jdk8Error=true;
        }
      }
      if ((!oracleJdbcError) && className.startsWith("oracle.sql.")) {
        try {
          String[] names=new String[]{"oracle.sql.DATE","oracle.sql.TIMESTAMP"};
          for (          String name : names) {
            if (name.equals(className)) {
              put(Class.forName(name),writer=DateCodec.instance);
              return writer;
            }
          }
        }
 catch (        Throwable e) {
          oracleJdbcError=true;
        }
      }
      if ((!springfoxError) && className.equals("springfox.documentation.spring.web.json.Json")) {
        try {
          put(Class.forName("springfox.documentation.spring.web.json.Json"),writer=SwaggerJsonSerializer.instance);
          return writer;
        }
 catch (        ClassNotFoundException e) {
          springfoxError=true;
        }
      }
      if ((!guavaError) && className.startsWith("com.google.common.collect.")) {
        try {
          String[] names=new String[]{"com.google.common.collect.HashMultimap","com.google.common.collect.LinkedListMultimap","com.google.common.collect.LinkedHashMultimap","com.google.common.collect.ArrayListMultimap","com.google.common.collect.TreeMultimap"};
          for (          String name : names) {
            if (name.equals(className)) {
              put(Class.forName(name),writer=GuavaCodec.instance);
              return writer;
            }
          }
        }
 catch (        ClassNotFoundException e) {
          guavaError=true;
        }
      }
      if ((!jsonnullError) && className.equals("net.sf.json.JSONNull")) {
        try {
          put(Class.forName("net.sf.json.JSONNull"),writer=MiscCodec.instance);
          return writer;
        }
 catch (        ClassNotFoundException e) {
          jsonnullError=true;
        }
      }
      if ((!jodaError) && className.startsWith("org.joda.")) {
        try {
          String[] names=new String[]{"org.joda.time.LocalDate","org.joda.time.LocalDateTime","org.joda.time.LocalTime","org.joda.time.Instant","org.joda.time.DateTime","org.joda.time.Period","org.joda.time.Duration","org.joda.time.DateTimeZone","org.joda.time.UTCDateTimeZone","org.joda.time.tz.CachedDateTimeZone","org.joda.time.tz.FixedDateTimeZone"};
          for (          String name : names) {
            if (name.equals(className)) {
              put(Class.forName(name),writer=JodaCodec.instance);
              return writer;
            }
          }
        }
 catch (        ClassNotFoundException e) {
          jodaError=true;
        }
      }
      if ("java.nio.HeapByteBuffer".equals(className)) {
        put(clazz,writer=ByteBufferCodec.instance);
        return writer;
      }
      if ("org.javamoney.moneta.Money".equals(className)) {
        put(clazz,writer=MonetaCodec.instance);
        return writer;
      }
      Class[] interfaces=clazz.getInterfaces();
      if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
        put(clazz,AnnotationSerializer.instance);
        return AnnotationSerializer.instance;
      }
      if (TypeUtils.isProxy(clazz)) {
        Class<?> superClazz=clazz.getSuperclass();
        ObjectSerializer superWriter=getObjectWriter(superClazz);
        put(clazz,superWriter);
        return superWriter;
      }
      if (Proxy.isProxyClass(clazz)) {
        Class handlerClass=null;
        if (interfaces.length == 2) {
          handlerClass=interfaces[1];
        }
 else {
          for (          Class proxiedInterface : interfaces) {
            if (proxiedInterface.getName().startsWith("org.springframework.aop.")) {
              continue;
            }
            if (handlerClass != null) {
              handlerClass=null;
              break;
            }
            handlerClass=proxiedInterface;
          }
        }
        if (handlerClass != null) {
          ObjectSerializer superWriter=getObjectWriter(handlerClass);
          put(clazz,superWriter);
          return superWriter;
        }
      }
      if (create) {
        writer=createJavaBeanSerializer(clazz);
        put(clazz,writer);
      }
    }
    if (writer == null) {
      writer=serializers.get(clazz);
    }
  }
  return writer;
}

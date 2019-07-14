public static List<FieldInfo> computeGetters(Class<?> clazz,JSONType jsonType,Map<String,String> aliasMap,Map<String,Field> fieldCacheMap,boolean sorted,PropertyNamingStrategy propertyNamingStrategy){
  Map<String,FieldInfo> fieldInfoMap=new LinkedHashMap<String,FieldInfo>();
  boolean kotlin=TypeUtils.isKotlin(clazz);
  Constructor[] constructors=null;
  Annotation[][] paramAnnotationArrays=null;
  String[] paramNames=null;
  short[] paramNameMapping=null;
  Method[] methods=clazz.getMethods();
  for (  Method method : methods) {
    String methodName=method.getName();
    int ordinal=0, serialzeFeatures=0, parserFeatures=0;
    String label=null;
    if (Modifier.isStatic(method.getModifiers())) {
      continue;
    }
    if (method.getReturnType().equals(Void.TYPE)) {
      continue;
    }
    if (method.getParameterTypes().length != 0) {
      continue;
    }
    if (method.getReturnType() == ClassLoader.class) {
      continue;
    }
    if (methodName.equals("getMetaClass") && method.getReturnType().getName().equals("groovy.lang.MetaClass")) {
      continue;
    }
    if (methodName.equals("getSuppressed") && method.getDeclaringClass() == Throwable.class) {
      continue;
    }
    if (kotlin && isKotlinIgnore(clazz,methodName)) {
      continue;
    }
    Boolean fieldAnnotationAndNameExists=false;
    JSONField annotation=method.getAnnotation(JSONField.class);
    if (annotation == null) {
      annotation=getSuperMethodAnnotation(clazz,method);
    }
    if (annotation == null && kotlin) {
      if (constructors == null) {
        constructors=clazz.getDeclaredConstructors();
        Constructor creatorConstructor=TypeUtils.getKoltinConstructor(constructors);
        if (creatorConstructor != null) {
          paramAnnotationArrays=creatorConstructor.getParameterAnnotations();
          paramNames=TypeUtils.getKoltinConstructorParameters(clazz);
          if (paramNames != null) {
            String[] paramNames_sorted=new String[paramNames.length];
            System.arraycopy(paramNames,0,paramNames_sorted,0,paramNames.length);
            Arrays.sort(paramNames_sorted);
            paramNameMapping=new short[paramNames.length];
            for (short p=0; p < paramNames.length; p++) {
              int index=Arrays.binarySearch(paramNames_sorted,paramNames[p]);
              paramNameMapping[index]=p;
            }
            paramNames=paramNames_sorted;
          }
        }
      }
      if (paramNames != null && paramNameMapping != null && methodName.startsWith("get")) {
        String propertyName=decapitalize(methodName.substring(3));
        int p=Arrays.binarySearch(paramNames,propertyName);
        if (p < 0) {
          for (int i=0; i < paramNames.length; i++) {
            if (propertyName.equalsIgnoreCase(paramNames[i])) {
              p=i;
              break;
            }
          }
        }
        if (p >= 0) {
          short index=paramNameMapping[p];
          Annotation[] paramAnnotations=paramAnnotationArrays[index];
          if (paramAnnotations != null) {
            for (            Annotation paramAnnotation : paramAnnotations) {
              if (paramAnnotation instanceof JSONField) {
                annotation=(JSONField)paramAnnotation;
                break;
              }
            }
          }
          if (annotation == null) {
            Field field=ParserConfig.getFieldFromCache(propertyName,fieldCacheMap);
            if (field != null) {
              annotation=field.getAnnotation(JSONField.class);
            }
          }
        }
      }
    }
    if (annotation != null) {
      if (!annotation.serialize()) {
        continue;
      }
      ordinal=annotation.ordinal();
      serialzeFeatures=SerializerFeature.of(annotation.serialzeFeatures());
      parserFeatures=Feature.of(annotation.parseFeatures());
      if (annotation.name().length() != 0) {
        String propertyName=annotation.name();
        if (aliasMap != null) {
          propertyName=aliasMap.get(propertyName);
          if (propertyName == null) {
            continue;
          }
        }
        FieldInfo fieldInfo=new FieldInfo(propertyName,method,null,clazz,null,ordinal,serialzeFeatures,parserFeatures,annotation,null,label);
        fieldInfoMap.put(propertyName,fieldInfo);
        continue;
      }
      if (annotation.label().length() != 0) {
        label=annotation.label();
      }
    }
    if (methodName.startsWith("get")) {
      if (methodName.length() < 4) {
        continue;
      }
      if (methodName.equals("getClass")) {
        continue;
      }
      if (methodName.equals("getDeclaringClass") && clazz.isEnum()) {
        continue;
      }
      char c3=methodName.charAt(3);
      String propertyName;
      if (Character.isUpperCase(c3) || c3 > 512) {
        if (compatibleWithJavaBean) {
          propertyName=decapitalize(methodName.substring(3));
        }
 else {
          propertyName=Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
        }
        propertyName=getPropertyNameByCompatibleFieldName(fieldCacheMap,methodName,propertyName,3);
      }
 else       if (c3 == '_') {
        propertyName=methodName.substring(4);
      }
 else       if (c3 == 'f') {
        propertyName=methodName.substring(3);
      }
 else       if (methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4))) {
        propertyName=decapitalize(methodName.substring(3));
      }
 else {
        continue;
      }
      boolean ignore=isJSONTypeIgnore(clazz,propertyName);
      if (ignore) {
        continue;
      }
      Field field=ParserConfig.getFieldFromCache(propertyName,fieldCacheMap);
      if (field == null && propertyName.length() > 1) {
        char ch=propertyName.charAt(1);
        if (ch >= 'A' && ch <= 'Z') {
          String javaBeanCompatiblePropertyName=decapitalize(methodName.substring(3));
          field=ParserConfig.getFieldFromCache(javaBeanCompatiblePropertyName,fieldCacheMap);
        }
      }
      JSONField fieldAnnotation=null;
      if (field != null) {
        fieldAnnotation=field.getAnnotation(JSONField.class);
        if (fieldAnnotation != null) {
          if (!fieldAnnotation.serialize()) {
            continue;
          }
          ordinal=fieldAnnotation.ordinal();
          serialzeFeatures=SerializerFeature.of(fieldAnnotation.serialzeFeatures());
          parserFeatures=Feature.of(fieldAnnotation.parseFeatures());
          if (fieldAnnotation.name().length() != 0) {
            fieldAnnotationAndNameExists=true;
            propertyName=fieldAnnotation.name();
            if (aliasMap != null) {
              propertyName=aliasMap.get(propertyName);
              if (propertyName == null) {
                continue;
              }
            }
          }
          if (fieldAnnotation.label().length() != 0) {
            label=fieldAnnotation.label();
          }
        }
      }
      if (aliasMap != null) {
        propertyName=aliasMap.get(propertyName);
        if (propertyName == null) {
          continue;
        }
      }
      if (propertyNamingStrategy != null && !fieldAnnotationAndNameExists) {
        propertyName=propertyNamingStrategy.translate(propertyName);
      }
      FieldInfo fieldInfo=new FieldInfo(propertyName,method,field,clazz,null,ordinal,serialzeFeatures,parserFeatures,annotation,fieldAnnotation,label);
      fieldInfoMap.put(propertyName,fieldInfo);
    }
    if (methodName.startsWith("is")) {
      if (methodName.length() < 3) {
        continue;
      }
      if (method.getReturnType() != Boolean.TYPE && method.getReturnType() != Boolean.class) {
        continue;
      }
      char c2=methodName.charAt(2);
      String propertyName;
      if (Character.isUpperCase(c2)) {
        if (compatibleWithJavaBean) {
          propertyName=decapitalize(methodName.substring(2));
        }
 else {
          propertyName=Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
        }
        propertyName=getPropertyNameByCompatibleFieldName(fieldCacheMap,methodName,propertyName,2);
      }
 else       if (c2 == '_') {
        propertyName=methodName.substring(3);
      }
 else       if (c2 == 'f') {
        propertyName=methodName.substring(2);
      }
 else {
        continue;
      }
      boolean ignore=isJSONTypeIgnore(clazz,propertyName);
      if (ignore) {
        continue;
      }
      Field field=ParserConfig.getFieldFromCache(propertyName,fieldCacheMap);
      if (field == null) {
        field=ParserConfig.getFieldFromCache(methodName,fieldCacheMap);
      }
      JSONField fieldAnnotation=null;
      if (field != null) {
        fieldAnnotation=field.getAnnotation(JSONField.class);
        if (fieldAnnotation != null) {
          if (!fieldAnnotation.serialize()) {
            continue;
          }
          ordinal=fieldAnnotation.ordinal();
          serialzeFeatures=SerializerFeature.of(fieldAnnotation.serialzeFeatures());
          parserFeatures=Feature.of(fieldAnnotation.parseFeatures());
          if (fieldAnnotation.name().length() != 0) {
            propertyName=fieldAnnotation.name();
            if (aliasMap != null) {
              propertyName=aliasMap.get(propertyName);
              if (propertyName == null) {
                continue;
              }
            }
          }
          if (fieldAnnotation.label().length() != 0) {
            label=fieldAnnotation.label();
          }
        }
      }
      if (aliasMap != null) {
        propertyName=aliasMap.get(propertyName);
        if (propertyName == null) {
          continue;
        }
      }
      if (propertyNamingStrategy != null) {
        propertyName=propertyNamingStrategy.translate(propertyName);
      }
      if (fieldInfoMap.containsKey(propertyName)) {
        continue;
      }
      FieldInfo fieldInfo=new FieldInfo(propertyName,method,field,clazz,null,ordinal,serialzeFeatures,parserFeatures,annotation,fieldAnnotation,label);
      fieldInfoMap.put(propertyName,fieldInfo);
    }
  }
  Field[] fields=clazz.getFields();
  computeFields(clazz,aliasMap,propertyNamingStrategy,fieldInfoMap,fields);
  return getFieldInfos(clazz,sorted,fieldInfoMap);
}

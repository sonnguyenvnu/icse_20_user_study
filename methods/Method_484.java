public boolean parseField(DefaultJSONParser parser,String key,Object object,Type objectType,Map<String,Object> fieldValues,int[] setFlags){
  JSONLexer lexer=parser.lexer;
  final int disableFieldSmartMatchMask=Feature.DisableFieldSmartMatch.mask;
  FieldDeserializer fieldDeserializer;
  if (lexer.isEnabled(disableFieldSmartMatchMask) || (this.beanInfo.parserFeatures & disableFieldSmartMatchMask) != 0) {
    fieldDeserializer=getFieldDeserializer(key);
  }
 else {
    fieldDeserializer=smartMatch(key,setFlags);
  }
  final int mask=Feature.SupportNonPublicField.mask;
  if (fieldDeserializer == null && (lexer.isEnabled(mask) || (this.beanInfo.parserFeatures & mask) != 0)) {
    if (this.extraFieldDeserializers == null) {
      ConcurrentHashMap extraFieldDeserializers=new ConcurrentHashMap<String,Object>(1,0.75f,1);
      for (Class c=this.clazz; c != null && c != Object.class; c=c.getSuperclass()) {
        Field[] fields=c.getDeclaredFields();
        for (        Field field : fields) {
          String fieldName=field.getName();
          if (this.getFieldDeserializer(fieldName) != null) {
            continue;
          }
          int fieldModifiers=field.getModifiers();
          if ((fieldModifiers & Modifier.FINAL) != 0 || (fieldModifiers & Modifier.STATIC) != 0) {
            continue;
          }
          extraFieldDeserializers.put(fieldName,field);
        }
      }
      this.extraFieldDeserializers=extraFieldDeserializers;
    }
    Object deserOrField=extraFieldDeserializers.get(key);
    if (deserOrField != null) {
      if (deserOrField instanceof FieldDeserializer) {
        fieldDeserializer=((FieldDeserializer)deserOrField);
      }
 else {
        Field field=(Field)deserOrField;
        field.setAccessible(true);
        FieldInfo fieldInfo=new FieldInfo(key,field.getDeclaringClass(),field.getType(),field.getGenericType(),field,0,0,0);
        fieldDeserializer=new DefaultFieldDeserializer(parser.getConfig(),clazz,fieldInfo);
        extraFieldDeserializers.put(key,fieldDeserializer);
      }
    }
  }
  if (fieldDeserializer == null) {
    if (!lexer.isEnabled(Feature.IgnoreNotMatch)) {
      throw new JSONException("setter not found, class " + clazz.getName() + ", property " + key);
    }
    int fieldIndex=-1;
    for (int i=0; i < this.sortedFieldDeserializers.length; i++) {
      FieldDeserializer fieldDeser=this.sortedFieldDeserializers[i];
      FieldInfo fieldInfo=fieldDeser.fieldInfo;
      if (fieldInfo.unwrapped && fieldDeser instanceof DefaultFieldDeserializer) {
        if (fieldInfo.field != null) {
          DefaultFieldDeserializer defaultFieldDeserializer=(DefaultFieldDeserializer)fieldDeser;
          ObjectDeserializer fieldValueDeser=defaultFieldDeserializer.getFieldValueDeserilizer(parser.getConfig());
          if (fieldValueDeser instanceof JavaBeanDeserializer) {
            JavaBeanDeserializer javaBeanFieldValueDeserializer=(JavaBeanDeserializer)fieldValueDeser;
            FieldDeserializer unwrappedFieldDeser=javaBeanFieldValueDeserializer.getFieldDeserializer(key);
            if (unwrappedFieldDeser != null) {
              Object fieldObject;
              try {
                fieldObject=fieldInfo.field.get(object);
                if (fieldObject == null) {
                  fieldObject=((JavaBeanDeserializer)fieldValueDeser).createInstance(parser,fieldInfo.fieldType);
                  fieldDeser.setValue(object,fieldObject);
                }
                lexer.nextTokenWithColon(defaultFieldDeserializer.getFastMatchToken());
                unwrappedFieldDeser.parseField(parser,fieldObject,objectType,fieldValues);
                fieldIndex=i;
              }
 catch (              Exception e) {
                throw new JSONException("parse unwrapped field error.",e);
              }
            }
          }
 else           if (fieldValueDeser instanceof MapDeserializer) {
            MapDeserializer javaBeanFieldValueDeserializer=(MapDeserializer)fieldValueDeser;
            Map fieldObject;
            try {
              fieldObject=(Map)fieldInfo.field.get(object);
              if (fieldObject == null) {
                fieldObject=javaBeanFieldValueDeserializer.createMap(fieldInfo.fieldType);
                fieldDeser.setValue(object,fieldObject);
              }
              lexer.nextTokenWithColon();
              Object fieldValue=parser.parse(key);
              fieldObject.put(key,fieldValue);
            }
 catch (            Exception e) {
              throw new JSONException("parse unwrapped field error.",e);
            }
            fieldIndex=i;
          }
        }
 else         if (fieldInfo.method.getParameterTypes().length == 2) {
          lexer.nextTokenWithColon();
          Object fieldValue=parser.parse(key);
          try {
            fieldInfo.method.invoke(object,key,fieldValue);
          }
 catch (          Exception e) {
            throw new JSONException("parse unwrapped field error.",e);
          }
          fieldIndex=i;
        }
      }
    }
    if (fieldIndex != -1) {
      if (setFlags != null) {
        int flagIndex=fieldIndex / 32;
        int bitIndex=fieldIndex % 32;
        setFlags[flagIndex]|=(1 << bitIndex);
      }
      return true;
    }
    parser.parseExtra(object,key);
    return false;
  }
  int fieldIndex=-1;
  for (int i=0; i < sortedFieldDeserializers.length; ++i) {
    if (sortedFieldDeserializers[i] == fieldDeserializer) {
      fieldIndex=i;
      break;
    }
  }
  if (fieldIndex != -1 && setFlags != null && key.startsWith("_")) {
    if (isSetFlag(fieldIndex,setFlags)) {
      parser.parseExtra(object,key);
      return false;
    }
  }
  lexer.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
  fieldDeserializer.parseField(parser,object,objectType,fieldValues);
  if (setFlags != null) {
    int flagIndex=fieldIndex / 32;
    int bitIndex=fieldIndex % 32;
    setFlags[flagIndex]|=(1 << bitIndex);
  }
  return true;
}

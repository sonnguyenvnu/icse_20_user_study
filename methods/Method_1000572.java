@SuppressWarnings({"deprecation","rawtypes"}) public static JsonEntityField eval(Mirror<?> mirror,Field fld){
  if (fld == null) {
    return null;
  }
  if (fld.getName().startsWith("$") && fld.getAnnotation(JsonField.class) == null)   return null;
  JsonField jf=fld.getAnnotation(JsonField.class);
  JsonEntityField jef=new JsonEntityField();
  jef.declaringClass=mirror.getType();
  jef.setGenericType(Lang.getFieldType(mirror,fld));
  jef.name=Strings.sBlank(null == jf ? null : jf.value(),fld.getName());
  jef.ejecting=mirror.getEjecting(fld.getName());
  jef.injecting=mirror.getInjecting(fld.getName());
  jef.mirror=Mirror.me(fld.getType());
  if (Modifier.isTransient(fld.getModifiers()) || (null != jf && jf.ignore())) {
    jef.setIgnore(true);
  }
  if (null != jf) {
    jef.setForceString(jf.forceString());
    String dataFormat=jf.dataFormat();
    if (Strings.isBlank(dataFormat)) {
      dataFormat=jf.dateFormat();
    }
    if (!Strings.isBlank(dataFormat)) {
      Mirror jfmirror=Mirror.me(jef.genericType);
      if (jfmirror.isNumber()) {
        jef.dataFormat=new DecimalFormat(dataFormat);
      }
 else       if (jfmirror.isDateTimeLike()) {
        DateFormat df=null;
        if (Strings.isBlank(jf.locale())) {
          df=new SimpleDateFormat(dataFormat);
        }
 else {
          df=new SimpleDateFormat(dataFormat,Locale.forLanguageTag(jf.locale()));
        }
        if (!Strings.isBlank(jf.timeZone())) {
          df.setTimeZone(TimeZone.getTimeZone(jf.timeZone()));
        }
        jef.dataFormat=df;
      }
    }
  }
  JsonIgnore jsonIgnore=fld.getAnnotation(JsonIgnore.class);
  if (jsonIgnore != null) {
    Mirror<?> fldMirror=Mirror.me(fld.getType());
    jef.isInt=fldMirror.isInt();
    jef.isDouble=fldMirror.isDouble() || fldMirror.isFloat();
    jef.hasJsonIgnore=true;
    if (jef.isDouble)     jef.ignoreNullDouble=jsonIgnore.null_double();
    if (jef.isInt)     jef.ignoreNullInt=jsonIgnore.null_int();
  }
  return jef;
}

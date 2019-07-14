public ObjectFormatter build(){
  Formatter formatter=field.getAnnotation(Formatter.class);
  if (formatter != null && !formatter.formatter().equals(Formatter.DEFAULT_FORMATTER)) {
    return initFormatter(formatter.formatter(),formatter.value());
  }
  if (formatter == null || formatter.subClazz().equals(Void.class)) {
    return initFormatterForType(field.getType(),formatter != null ? formatter.value() : null);
  }
 else {
    return initFormatterForType(formatter.subClazz(),formatter.value());
  }
}

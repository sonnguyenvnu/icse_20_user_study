@Override public Enum cast(Number src,Class<?> toType,String... args) throws FailToCastObjectException {
  int v=src.intValue();
  Enum o=null;
  try {
    Method m=toType.getMethod("fromInt",int.class);
    if (Modifier.isStatic(m.getModifiers()) && toType.isAssignableFrom(m.getReturnType())) {
      o=(Enum)m.invoke(null,v);
    }
  }
 catch (  Exception e) {
  }
  if (null == o) {
    try {
      Method m=toType.getMethod("from",int.class);
      if (Modifier.isStatic(m.getModifiers()) && toType.isAssignableFrom(m.getReturnType())) {
        o=(Enum)m.invoke(null,v);
      }
    }
 catch (    Exception e) {
    }
  }
  if (null == o)   try {
    for (    Field field : toType.getFields()) {
      if (field.getType() == toType) {
        Enum em=(Enum)field.get(null);
        if (em.ordinal() == v)         return em;
      }
    }
    throw Lang.makeThrow(FailToCastObjectException.class,"Can NO find enum value in [%s] by int value '%d'",toType.getName(),src.intValue());
  }
 catch (  Exception e2) {
    throw Lang.wrapThrow(e2,FailToCastObjectException.class);
  }
  return o;
}

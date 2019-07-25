@SuppressWarnings("unchecked") public ValueProxy make(IocMaking ing,IocValue iv){
  Object value=iv.getValue();
  String type=iv.getType();
  if ("null".equals(type) || null == value) {
    return new StaticValue(null);
  }
  if (value instanceof ValueProxy) {
    return (ValueProxy)value;
  }
 else   if ("normal".equals(type) || null == type) {
    if (value.getClass().isArray()) {
      Object[] vs=(Object[])value;
      IocValue[] tmp=new IocValue[vs.length];
      for (int i=0; i < tmp.length; i++)       tmp[i]=(IocValue)vs[i];
      return new ArrayValue(ing,tmp);
    }
 else     if (value instanceof Map<?,?>) {
      return new MapValue(ing,(Map<String,IocValue>)value,(Class<? extends Map<String,Object>>)value.getClass());
    }
 else     if (value instanceof Collection<?>) {
      return new CollectionValue(ing,(Collection<IocValue>)value,(Class<? extends Collection<Object>>)value.getClass());
    }
 else     if (value instanceof IocObject) {
      return new InnerValue((IocObject)value);
    }
    return new StaticValue(value);
  }
 else   if ("refer".equals(type)) {
    String s=value.toString();
    if (null != s) {
      String renm=s.toLowerCase();
      if ("$ioc".equals(renm)) {
        return new IocSelfValue();
      }
 else       if ("$name".equals(renm)) {
        return new ObjectNameValue();
      }
 else       if ("$context".equals(renm)) {
        return new IocContextObjectValue();
      }
    }
    return new ReferValue(s);
  }
 else   if (IocValue.TYPE_REFER_TYPE.equals(type)) {
    if (value instanceof CharSequence) {
      String[] tmp=value.toString().split("#");
      return new ReferTypeValue(tmp[0],Lang.forName(tmp[1],Object.class));
    }
 else     if (value instanceof Field) {
      return new ReferTypeValue((Field)value);
    }
    throw new IocException(ing.getObjectName(),"unspported refer_type:'%s'",value);
  }
 else   if ("java".equals(type)) {
    return new JavaValue(value.toString());
  }
 else   if ("file".equals(type)) {
    return new FileValue(value.toString());
  }
 else   if ("env".equals(type)) {
    return new EnvValue(value);
  }
 else   if ("sys".equals(type)) {
    return new SysPropValue(value);
  }
 else   if ("inner".equals(type)) {
    return new InnerValue((IocObject)value);
  }
 else   if ("jndi".equals(type)) {
    return new JNDI_Value(value.toString());
  }
 else   if ("el".equals(type)) {
    return new EL_Value(value.toString());
  }
  return null;
}

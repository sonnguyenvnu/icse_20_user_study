@Override public Number cast(Enum src,Class<?> toType,String... args) throws FailToCastObjectException {
  Mirror<?> mi=Mirror.me(src);
  try {
    return (Number)mi.invoke(src,"value");
  }
 catch (  Exception e) {
    Integer re=src.ordinal();
    if (toType.isPrimitive() || toType.equals(Integer.class) || toType.isAssignableFrom(Number.class)) {
      return re;
    }
    return (Number)Mirror.me(toType).born(re.toString());
  }
}

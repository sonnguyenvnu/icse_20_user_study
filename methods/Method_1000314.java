@SuppressWarnings("unchecked") @Override public Map cast(Object src,Class<?> toType,String... args) throws FailToCastObjectException {
  return Lang.obj2map(src,(Class<? extends Map>)((Class<? extends Map>)toType));
}

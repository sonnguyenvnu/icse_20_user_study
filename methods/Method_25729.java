static ImmutableMap<TypeKind,String> getGuavaUtils(){
  Map<TypeKind,String> guavaUtils=new EnumMap<>(TypeKind.class);
  guavaUtils.put(TypeKind.BOOLEAN,"Booleans");
  guavaUtils.put(TypeKind.BYTE,"Bytes");
  guavaUtils.put(TypeKind.SHORT,"Shorts");
  guavaUtils.put(TypeKind.INT,"Ints");
  guavaUtils.put(TypeKind.LONG,"Longs");
  guavaUtils.put(TypeKind.CHAR,"Chars");
  guavaUtils.put(TypeKind.FLOAT,"Floats");
  guavaUtils.put(TypeKind.DOUBLE,"Doubles");
  return ImmutableMap.copyOf(guavaUtils);
}

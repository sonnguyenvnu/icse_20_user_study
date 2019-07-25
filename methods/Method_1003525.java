private void register(String sourceTypeName,Class<?> targetClass,ConversionProvider conversion){
  Type sourceType=typeFactory.getType(sourceTypeName);
  Type targetType=typeFactory.getType(targetClass);
  conversions.put(new Key(sourceType,targetType),conversion);
  conversions.put(new Key(targetType,sourceType),reverse(conversion));
}

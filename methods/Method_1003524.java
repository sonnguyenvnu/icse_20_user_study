private void register(Class<?> sourceClass,Class<?> targetClass,ConversionProvider conversion){
  Type sourceType=typeFactory.getType(sourceClass);
  Type targetType=typeFactory.getType(targetClass);
  conversions.put(new Key(sourceType,targetType),conversion);
  conversions.put(new Key(targetType,sourceType),reverse(conversion));
}

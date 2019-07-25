@Override public Enum<?> generate(SourceOfRandomness random,GenerationStatus status){
  Object[] values=enumType.getEnumConstants();
  int index=turnOffRandomness == null ? random.nextInt(0,values.length - 1) : status.attempts() % values.length;
  return (Enum<?>)values[index];
}

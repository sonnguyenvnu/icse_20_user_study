@Override public Object generate(SourceOfRandomness random,GenerationStatus status){
  int length=length(random,status);
  Object array=Array.newInstance(componentType,length);
  Stream<?> items=Stream.generate(() -> component.generate(random,status)).sequential();
  if (distinct)   items=items.distinct();
  Iterator<?> iterator=items.iterator();
  for (int i=0; i < length; ++i)   Array.set(array,i,iterator.next());
  return array;
}

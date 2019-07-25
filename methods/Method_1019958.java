static <A,B>Schema<Tuple2<A,B>> schema(TypeSafeKey<?,A> aKey,TypeSafeKey<?,B> bKey){
  return schema(bKey).add(aKey);
}

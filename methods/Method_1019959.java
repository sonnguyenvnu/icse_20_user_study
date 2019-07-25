static <A,B,C>Schema<Tuple3<A,B,C>> schema(TypeSafeKey<?,A> aKey,TypeSafeKey<?,B> bKey,TypeSafeKey<?,C> cKey){
  return schema(bKey,cKey).add(aKey);
}

static <A,B,C,D>Schema<Tuple4<A,B,C,D>> schema(TypeSafeKey<?,A> aKey,TypeSafeKey<?,B> bKey,TypeSafeKey<?,C> cKey,TypeSafeKey<?,D> dKey){
  return schema(bKey,cKey,dKey).add(aKey);
}

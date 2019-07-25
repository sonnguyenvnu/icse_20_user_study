static <A,B,C,D,E>Schema<Tuple5<A,B,C,D,E>> schema(TypeSafeKey<?,A> aKey,TypeSafeKey<?,B> bKey,TypeSafeKey<?,C> cKey,TypeSafeKey<?,D> dKey,TypeSafeKey<?,E> eKey){
  return schema(bKey,cKey,dKey,eKey).add(aKey);
}

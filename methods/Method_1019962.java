static <A,B,C,D,E,F>Schema<Tuple6<A,B,C,D,E,F>> schema(TypeSafeKey<?,A> aKey,TypeSafeKey<?,B> bKey,TypeSafeKey<?,C> cKey,TypeSafeKey<?,D> dKey,TypeSafeKey<?,E> eKey,TypeSafeKey<?,F> fKey){
  return schema(bKey,cKey,dKey,eKey,fKey).add(aKey);
}

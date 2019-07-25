static <A,B,C,D,E,F,G,H>Schema<Tuple8<A,B,C,D,E,F,G,H>> schema(TypeSafeKey<?,A> aKey,TypeSafeKey<?,B> bKey,TypeSafeKey<?,C> cKey,TypeSafeKey<?,D> dKey,TypeSafeKey<?,E> eKey,TypeSafeKey<?,F> fKey,TypeSafeKey<?,G> gKey,TypeSafeKey<?,H> hKey){
  return schema(bKey,cKey,dKey,eKey,fKey,gKey,hKey).add(aKey);
}

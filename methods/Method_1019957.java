@SuppressWarnings({"unchecked","RedundantTypeArguments"}) default <A,NewValues extends HCons<A,Values>>Schema<NewValues> add(TypeSafeKey<?,A> key){
  Lens<HMap,HMap,Maybe<NewValues>,Maybe<NewValues>> lens=Lens.both(this,valueAt(key)).<Maybe<NewValues>>mapA(into((maybeValues,maybeA) -> maybeValues.zip(maybeA.fmap(a -> values -> (NewValues)values.cons(a))))).<Maybe<NewValues>>mapB(Both.both(maybeNewValues -> maybeNewValues.fmap(HCons<A,Values>::tail),maybeNewValues -> maybeNewValues.fmap(HCons<A,Values>::head)));
  return new Schema<NewValues>(){
    @Override public <CoP extends Profunctor<?,?,? extends Cartesian<?,?,?>>,CoF extends Functor<?,? extends Functor<?,?>>,FB extends Functor<Maybe<NewValues>,? extends CoF>,FT extends Functor<HMap,? extends CoF>,PAFB extends Profunctor<Maybe<NewValues>,FB,? extends CoP>,PSFT extends Profunctor<HMap,FT,? extends CoP>>PSFT apply(    PAFB pafb){
      return lens.apply(pafb);
    }
  }
;
}

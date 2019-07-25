@SuppressWarnings("overloads") public static <P extends Profunctor<?,?,? extends P>,S,T,A,B>Optic<P,Const<Maybe<A>,?>,S,T,Maybe<A>,B> pre(ProtoOptic<P,S,T,A,B> protoOptic){
  return pre(protoOptic.toOptic(new Pure<Const<Maybe<A>,?>>(){
    @Override public <X>Const<Maybe<A>,X> checkedApply(    X x){
      return new Const<>(nothing());
    }
  }
));
}

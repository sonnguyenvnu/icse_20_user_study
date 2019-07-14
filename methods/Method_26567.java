private static <T1,T2,T3,R>Choice<State<R>> chooseSubtrees(State<?> state,Function<State<?>,Choice<? extends State<? extends T1>>> choice1,Function<State<?>,Choice<? extends State<? extends T2>>> choice2,Function<State<?>,Choice<? extends State<? extends T3>>> choice3,TriFunction<T1,T2,T3,R> finalizer){
  return choice1.apply(state).thenChoose(s1 -> choice2.apply(s1).thenChoose(s2 -> choice3.apply(s2).transform(s3 -> s3.withResult(finalizer.apply(s1.result(),s2.result(),s3.result())))));
}

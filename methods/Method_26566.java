/** 
 * This method, and its overloads, take <ol> <li>an initial state <li>functions that, given one state, return a branch choosing a subtree <li>a function that takes pieces of a tree type and recomposes them </ol>
 */
private static <T,R>Choice<State<R>> chooseSubtrees(State<?> state,Function<State<?>,Choice<? extends State<? extends T>>> choice1,Function<T,R> finalizer){
  return choice1.apply(state).transform(s -> s.withResult(finalizer.apply(s.result())));
}

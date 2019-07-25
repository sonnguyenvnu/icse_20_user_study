/** 
 * Builds a  {@link ReactiveGuard} from a {@link Guard}.
 * @param < S > the type of state
 * @param < E > the type of event
 * @param guard the guard
 * @return the function
 */
public static <S,E>Function<StateContext<S,E>,Mono<Boolean>> from(Guard<S,E> guard){
  if (guard != null) {
    return context -> Mono.fromSupplier(() -> guard.evaluate(context));
  }
 else {
    return null;
  }
}

/** 
 * Emits a boolean that determines if the user is logged in or not. The returned observable will emit immediately with the logged in state, and then again each time the current user is updated.
 */
public @NonNull Observable<Boolean> isLoggedIn(){
  return observable().map(ObjectUtils::isNotNull);
}

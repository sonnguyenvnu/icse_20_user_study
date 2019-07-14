/** 
 * Emits only values of a logged in user. The returned observable may never emit.
 */
public @NonNull Observable<User> loggedInUser(){
  return observable().filter(ObjectUtils::isNotNull);
}

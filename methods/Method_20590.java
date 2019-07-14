/** 
 * Emits only values of a logged out user. The returned observable may never emit.
 */
public @NonNull Observable<User> loggedOutUser(){
  return observable().filter(ObjectUtils::isNull);
}

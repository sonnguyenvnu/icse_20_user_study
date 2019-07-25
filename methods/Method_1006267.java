/** 
 * Creates an Optional<Eprint> from various schemes including URL. Useful for suppressing the <c>IllegalArgumentException</c> of the Constructor and checking for Optional.isPresent() instead.
 * @param eprint the Eprint string
 * @return an Optional containing the Eprint or an empty Optional
 */
public static Optional<Eprint> build(String eprint){
  try {
    return Optional.ofNullable(new Eprint(eprint));
  }
 catch (  IllegalArgumentException|NullPointerException e) {
    return Optional.empty();
  }
}

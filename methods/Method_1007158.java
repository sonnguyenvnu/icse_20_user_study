/** 
 * Returns a function that constructs an either with a validation.
 * @return A function that constructs an either with a validation.
 */
public static <E,T>F<Validation<E,T>,Either<E,T>> either(){
  return Validation::toEither;
}

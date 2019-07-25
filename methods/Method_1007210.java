/** 
 * Parses the input to produce a result or error.
 * @param i The input to parse.
 * @return A parse result with the remaining input or an error.
 */
public Validation<E,Result<I,A>> parse(final I i){
  return f.f(i);
}

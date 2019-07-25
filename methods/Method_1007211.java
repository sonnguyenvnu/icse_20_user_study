/** 
 * Returns a parser that negates this parser. If this parser succeeds, then the returned parser fails and vice versa.
 * @param e The error message to fail with if this parser succeeds.
 * @return A parser that negates this parser.
 */
public Parser<I,Unit,E> not(final F0<E> e){
  return parser(i -> parse(i).isFail() ? Validation.success(result(i,unit())) : Validation.fail(e.f()));
}

/** 
 * Returns a unary operator that always returns its input argument.
 * @return a unary operator that always returns its input argument
 */
static ThrowingDoubleUnaryOperator identity(){
  return t -> t;
}

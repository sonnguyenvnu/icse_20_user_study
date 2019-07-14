/** 
 * Applies this operator to the given operand.
 * @param operand the operand
 * @return the operator result
 */
default int applyAsInt(int operand){
  try {
    return applyAsIntThrows(operand);
  }
 catch (  Exception e) {
    throw new FunctionalException(e);
  }
}

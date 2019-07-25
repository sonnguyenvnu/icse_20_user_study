/** 
 * Returns the product of a list of integers.
 * @param ints A list of integers to multiply together.
 * @return The product of the integers in the list.
 */
public static int product(final List<Integer> ints){
  return Monoid.intMultiplicationMonoid.sumLeft(ints);
}

/** 
 * Gives a random element of the given array.
 * @param < T > type of items in the array
 * @param items an array
 * @return a randomly chosen element from the array
 */
public <T>T choose(T[] items){
  return items[nextInt(items.length)];
}

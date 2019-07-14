/** 
 * Asserts that a Collection is not null and of a certain size
 * @param input The input under test
 * @param message The message for any exception
 */
public static void hasSize(Collection<?> input,int length,String message){
  notNull(input,message);
  if (input.size() != length) {
    throw new IllegalArgumentException(message);
  }
}

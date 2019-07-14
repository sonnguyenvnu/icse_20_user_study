/** 
 * Asserts that a String is not null and of a certain length
 * @param input The input under test
 * @param message The message for any exception
 */
public static void hasLength(String input,int length,String message){
  notNull(input,message);
  if (input.trim().length() != length) {
    throw new IllegalArgumentException(message);
  }
}

/** 
 * If called with a  {@code null} array or one or more {@code null} elements in {@code objects}, the test will halt and be ignored.
 */
public static void assumeNotNull(Object... objects){
  assumeThat(objects,notNullValue());
  assumeThat(asList(objects),everyItem(notNullValue()));
}

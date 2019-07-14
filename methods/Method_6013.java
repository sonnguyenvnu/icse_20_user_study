/** 
 * Returns a string with comma delimited simple names of each object's class.
 * @param objects The objects whose simple class names should be comma delimited and returned.
 * @return A string with comma delimited simple names of each object's class.
 */
public static String getCommaDelimitedSimpleClassNames(Object[] objects){
  StringBuilder stringBuilder=new StringBuilder();
  for (int i=0; i < objects.length; i++) {
    stringBuilder.append(objects[i].getClass().getSimpleName());
    if (i < objects.length - 1) {
      stringBuilder.append(", ");
    }
  }
  return stringBuilder.toString();
}

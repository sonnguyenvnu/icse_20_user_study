/** 
 * Verify that input values are within specified bounds.
 * @param value  the value to check
 * @param lowerBound  the lower bound allowed for value
 * @param upperBound  the upper bound allowed for value
 * @throws IllegalFieldValueException if value is not in the specified bounds
 */
public static void verifyValueBounds(String fieldName,int value,int lowerBound,int upperBound){
  if ((value < lowerBound) || (value > upperBound)) {
    throw new IllegalFieldValueException(fieldName,Integer.valueOf(value),Integer.valueOf(lowerBound),Integer.valueOf(upperBound));
  }
}

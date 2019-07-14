/** 
 * Checks whether a field type is supported, and if so adds the new value to the relevant index in the specified array.
 * @param type  the field type
 * @param values  the array to update
 * @param newValue  the new value to store if successful
 */
private void checkAndUpdate(DurationFieldType type,int[] values,int newValue){
  int index=indexOf(type);
  if (index == -1) {
    if (newValue != 0) {
      throw new IllegalArgumentException("Period does not support field '" + type.getName() + "'");
    }
  }
 else {
    values[index]=newValue;
  }
}

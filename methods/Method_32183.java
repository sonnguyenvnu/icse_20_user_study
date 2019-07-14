/** 
 * Adds a value (which may be negative) to the partial instant, throwing an exception if the maximum size of the instant is reached. <p> The value will be added to this field, overflowing into larger fields if necessary. Smaller fields should be unaffected, except where the result would be an invalid value for a smaller field. In this case the smaller field is adjusted to be in range. <p> Partial instants only contain some fields. This may result in a maximum possible value, such as TimeOfDay being limited to 23:59:59:999. If this limit is breached by the add an exception is thrown. <p> For example, in the ISO chronology:<br> 2000-08-20 add six months is 2000-02-20<br> 2000-08-20 add twenty months is 2000-04-20<br> 2000-08-20 add minus nine months is 2000-11-20<br> 2001-01-31 add one month  is 2001-02-28<br> 2001-01-31 add two months is 2001-03-31<br>
 * @param instant  the partial instant
 * @param fieldIndex  the index of this field in the partial
 * @param values  the values of the partial instant which should be updated
 * @param valueToAdd  the value to add, in the units of the field
 * @return the passed in values
 * @throws IllegalArgumentException if the value is invalid or the maximum instant is reached
 */
public int[] add(ReadablePartial instant,int fieldIndex,int[] values,int valueToAdd){
  if (valueToAdd == 0) {
    return values;
  }
  DateTimeField nextField=null;
  while (valueToAdd > 0) {
    int max=getMaximumValue(instant,values);
    long proposed=values[fieldIndex] + valueToAdd;
    if (proposed <= max) {
      values[fieldIndex]=(int)proposed;
      break;
    }
    if (nextField == null) {
      if (fieldIndex == 0) {
        throw new IllegalArgumentException("Maximum value exceeded for add");
      }
      nextField=instant.getField(fieldIndex - 1);
      if (getRangeDurationField().getType() != nextField.getDurationField().getType()) {
        throw new IllegalArgumentException("Fields invalid for add");
      }
    }
    valueToAdd-=(max + 1) - values[fieldIndex];
    values=nextField.add(instant,fieldIndex - 1,values,1);
    values[fieldIndex]=getMinimumValue(instant,values);
  }
  while (valueToAdd < 0) {
    int min=getMinimumValue(instant,values);
    long proposed=values[fieldIndex] + valueToAdd;
    if (proposed >= min) {
      values[fieldIndex]=(int)proposed;
      break;
    }
    if (nextField == null) {
      if (fieldIndex == 0) {
        throw new IllegalArgumentException("Maximum value exceeded for add");
      }
      nextField=instant.getField(fieldIndex - 1);
      if (getRangeDurationField().getType() != nextField.getDurationField().getType()) {
        throw new IllegalArgumentException("Fields invalid for add");
      }
    }
    valueToAdd-=(min - 1) - values[fieldIndex];
    values=nextField.add(instant,fieldIndex - 1,values,-1);
    values[fieldIndex]=getMaximumValue(instant,values);
  }
  return set(instant,fieldIndex,values,values[fieldIndex]);
}

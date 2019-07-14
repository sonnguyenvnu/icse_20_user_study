/** 
 * Adds a value (which may be negative) to the partial instant, wrapping the whole partial if the maximum size of the partial is reached. <p> The value will be added to this field, overflowing into larger fields if necessary. Smaller fields should be unaffected, except where the result would be an invalid value for a smaller field. In this case the smaller field is adjusted to be in range. <p> Partial instants only contain some fields. This may result in a maximum possible value, such as TimeOfDay normally being limited to 23:59:59:999. If this limit is reached by the addition, this method will wrap back to 00:00:00.000. In fact, you would generally only use this method for classes that have a limitation such as this. <p> For example, in the ISO chronology:<br> 10:20:30 add 20 minutes is 10:40:30<br> 10:20:30 add 45 minutes is 11:05:30<br> 10:20:30 add 16 hours is 02:20:30<br>
 * @param instant  the partial instant
 * @param fieldIndex  the index of this field in the partial
 * @param values  the values of the partial instant which should be updated
 * @param valueToAdd  the value to add, in the units of the field
 * @return the passed in values
 * @throws IllegalArgumentException if the value is invalid or the maximum instant is reached
 */
public int[] addWrapPartial(ReadablePartial instant,int fieldIndex,int[] values,int valueToAdd){
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
        valueToAdd-=(max + 1) - values[fieldIndex];
        values[fieldIndex]=getMinimumValue(instant,values);
        continue;
      }
      nextField=instant.getField(fieldIndex - 1);
      if (getRangeDurationField().getType() != nextField.getDurationField().getType()) {
        throw new IllegalArgumentException("Fields invalid for add");
      }
    }
    valueToAdd-=(max + 1) - values[fieldIndex];
    values=nextField.addWrapPartial(instant,fieldIndex - 1,values,1);
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
        valueToAdd-=(min - 1) - values[fieldIndex];
        values[fieldIndex]=getMaximumValue(instant,values);
        continue;
      }
      nextField=instant.getField(fieldIndex - 1);
      if (getRangeDurationField().getType() != nextField.getDurationField().getType()) {
        throw new IllegalArgumentException("Fields invalid for add");
      }
    }
    valueToAdd-=(min - 1) - values[fieldIndex];
    values=nextField.addWrapPartial(instant,fieldIndex - 1,values,-1);
    values[fieldIndex]=getMaximumValue(instant,values);
  }
  return set(instant,fieldIndex,values,values[fieldIndex]);
}

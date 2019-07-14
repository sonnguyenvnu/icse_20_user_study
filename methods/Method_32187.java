/** 
 * Sets a value using the specified partial instant. <p> The value of this field (specified by the index) will be set. If the value is invalid, an exception if thrown. <p> If setting this field would make other fields invalid, then those fields may be changed. For example if the current date is the 31st January, and the month is set to February, the day would be invalid. Instead, the day would be changed to the closest value - the 28th/29th February as appropriate.
 * @param partial  the partial instant
 * @param fieldIndex  the index of this field in the instant
 * @param values  the values to update
 * @param newValue  the value to set, in the units of the field
 * @return the updated values
 * @throws IllegalArgumentException if the value is invalid
 */
public int[] set(ReadablePartial partial,int fieldIndex,int[] values,int newValue){
  FieldUtils.verifyValueBounds(this,newValue,getMinimumValue(partial,values),getMaximumValue(partial,values));
  values[fieldIndex]=newValue;
  for (int i=fieldIndex + 1; i < partial.size(); i++) {
    DateTimeField field=partial.getField(i);
    if (values[i] > field.getMaximumValue(partial,values)) {
      values[i]=field.getMaximumValue(partial,values);
    }
    if (values[i] < field.getMinimumValue(partial,values)) {
      values[i]=field.getMinimumValue(partial,values);
    }
  }
  return values;
}

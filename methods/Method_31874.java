/** 
 * Validates whether the fields stored in a partial instant are valid. <p> This implementation uses  {@link DateTimeField#getMinimumValue(ReadablePartial,int[])}and  {@link DateTimeField#getMaximumValue(ReadablePartial,int[])}.
 * @param partial  the partial instant to validate
 * @param values  the values to validate, not null unless the partial is empty
 * @throws IllegalArgumentException if the instant is invalid
 */
public void validate(ReadablePartial partial,int[] values){
  int size=partial.size();
  for (int i=0; i < size; i++) {
    int value=values[i];
    DateTimeField field=partial.getField(i);
    if (value < field.getMinimumValue()) {
      throw new IllegalFieldValueException(field.getType(),Integer.valueOf(value),Integer.valueOf(field.getMinimumValue()),null);
    }
    if (value > field.getMaximumValue()) {
      throw new IllegalFieldValueException(field.getType(),Integer.valueOf(value),null,Integer.valueOf(field.getMaximumValue()));
    }
  }
  for (int i=0; i < size; i++) {
    int value=values[i];
    DateTimeField field=partial.getField(i);
    if (value < field.getMinimumValue(partial,values)) {
      throw new IllegalFieldValueException(field.getType(),Integer.valueOf(value),Integer.valueOf(field.getMinimumValue(partial,values)),null);
    }
    if (value > field.getMaximumValue(partial,values)) {
      throw new IllegalFieldValueException(field.getType(),Integer.valueOf(value),null,Integer.valueOf(field.getMaximumValue(partial,values)));
    }
  }
}

/** 
 * Gets a copy of this date with the specified field removed. <p> If this partial did not previously support the field, no error occurs.
 * @param fieldType  the field type to remove, may be null
 * @return a copy of this instance with the field removed
 */
public Partial without(DateTimeFieldType fieldType){
  int index=indexOf(fieldType);
  if (index != -1) {
    DateTimeFieldType[] newTypes=new DateTimeFieldType[size() - 1];
    int[] newValues=new int[size() - 1];
    System.arraycopy(iTypes,0,newTypes,0,index);
    System.arraycopy(iTypes,index + 1,newTypes,index,newTypes.length - index);
    System.arraycopy(iValues,0,newValues,0,index);
    System.arraycopy(iValues,index + 1,newValues,index,newValues.length - index);
    Partial newPartial=new Partial(iChronology,newTypes,newValues);
    iChronology.validate(newPartial,newValues);
    return newPartial;
  }
  return this;
}

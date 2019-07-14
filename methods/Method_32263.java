public long set(long millis,int value){
  FieldUtils.verifyValueBounds(this,value,iMinValue,getMaximumValue());
  if (value <= iSkip) {
    if (value == iSkip) {
      throw new IllegalFieldValueException(DateTimeFieldType.year(),Integer.valueOf(value),null,null);
    }
    value++;
  }
  return super.set(millis,value);
}

/** 
 * Computes the parsed datetime by setting the saved fields. This method is idempotent, but it is not thread-safe.
 * @param resetFields false by default, but when true, unsaved field values are cleared
 * @param text optional text being parsed, to be included in any error message
 * @return milliseconds since 1970-01-01T00:00:00Z
 * @throws IllegalArgumentException if any field is out of range
 * @since 2.4
 */
public long computeMillis(boolean resetFields,CharSequence text){
  SavedField[] savedFields=iSavedFields;
  int count=iSavedFieldsCount;
  if (iSavedFieldsShared) {
    iSavedFields=savedFields=(SavedField[])iSavedFields.clone();
    iSavedFieldsShared=false;
  }
  sort(savedFields,count);
  if (count > 0) {
    DurationField months=DurationFieldType.months().getField(iChrono);
    DurationField days=DurationFieldType.days().getField(iChrono);
    DurationField first=savedFields[0].iField.getDurationField();
    if (compareReverse(first,months) >= 0 && compareReverse(first,days) <= 0) {
      saveField(DateTimeFieldType.year(),iDefaultYear);
      return computeMillis(resetFields,text);
    }
  }
  long millis=iMillis;
  try {
    for (int i=0; i < count; i++) {
      millis=savedFields[i].set(millis,resetFields);
    }
    if (resetFields) {
      for (int i=0; i < count; i++) {
        if (!savedFields[i].iField.isLenient()) {
          millis=savedFields[i].set(millis,i == (count - 1));
        }
      }
    }
  }
 catch (  IllegalFieldValueException e) {
    if (text != null) {
      e.prependMessage("Cannot parse \"" + text + '"');
    }
    throw e;
  }
  if (iOffset != null) {
    millis-=iOffset;
  }
 else   if (iZone != null) {
    int offset=iZone.getOffsetFromLocal(millis);
    millis-=offset;
    if (offset != iZone.getOffset(millis)) {
      String message="Illegal instant due to time zone offset transition (" + iZone + ')';
      if (text != null) {
        message="Cannot parse \"" + text + "\": " + message;
      }
      throw new IllegalInstantException(message);
    }
  }
  return millis;
}

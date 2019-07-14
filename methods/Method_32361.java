/** 
 * Saves a datetime field value.
 * @param fieldType  the field type
 * @param value  the value
 */
public void saveField(DateTimeFieldType fieldType,int value){
  obtainSaveField().init(fieldType.getField(iChrono),value);
}

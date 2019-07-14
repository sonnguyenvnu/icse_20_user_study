/** 
 * Saves a datetime field text value.
 * @param fieldType  the field type
 * @param text  the text value
 * @param locale  the locale to use
 */
public void saveField(DateTimeFieldType fieldType,String text,Locale locale){
  obtainSaveField().init(fieldType.getField(iChrono),text,locale);
}

/** 
 * Get ExcelWriter with a template file
 * @param temp         Append data after a POI file , Can be null?the template POI filesystem that contains theWorkbook stream)
 * @param outputStream the java OutputStream you wish to write the data to
 * @param typeEnum     03 or 07
 * @param needHead
 * @param handler      User-defined callback
 * @return new  ExcelWriter
 */
public static ExcelWriter getWriterWithTempAndHandler(InputStream temp,OutputStream outputStream,ExcelTypeEnum typeEnum,boolean needHead,WriteHandler handler){
  return new ExcelWriter(temp,outputStream,typeEnum,needHead,handler);
}

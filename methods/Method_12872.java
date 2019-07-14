/** 
 * Get ExcelReader.
 * @param in       the POI filesystem that contains the Workbook stream.
 * @param listener Callback method after each row is parsed.
 * @return ExcelReader.
 */
public static ExcelReader getReader(InputStream in,AnalysisEventListener listener){
  return new ExcelReader(in,null,listener);
}

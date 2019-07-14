/** 
 * Get ExcelWriter
 * @param outputStream the java OutputStream you wish to write the data to.
 * @return new ExcelWriter.
 */
public static ExcelWriter getWriter(OutputStream outputStream){
  return new ExcelWriter(outputStream,ExcelTypeEnum.XLSX,true);
}

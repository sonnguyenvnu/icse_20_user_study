/** 
 * Parse the specified sheet
 * @param sheet  Read sheet
 * @param clazz object parsed into each row of data
 */
@Deprecated public void read(Sheet sheet,Class<? extends BaseRowModel> clazz){
  sheet.setClazz(clazz);
  analyser.analysis(sheet);
}

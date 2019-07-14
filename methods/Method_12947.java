/** 
 * @param workbook
 * @param f
 * @param indexedColors
 * @return
 */
public static CellStyle buildCellStyle(Workbook workbook,com.alibaba.excel.metadata.Font f,IndexedColors indexedColors){
  CellStyle cellStyle=buildDefaultCellStyle(workbook);
  if (f != null) {
    Font font=workbook.createFont();
    font.setFontName(f.getFontName());
    font.setFontHeightInPoints(f.getFontHeightInPoints());
    font.setBold(f.isBold());
    cellStyle.setFont(font);
  }
  if (indexedColors != null) {
    cellStyle.setFillForegroundColor(indexedColors.getIndex());
  }
  return cellStyle;
}

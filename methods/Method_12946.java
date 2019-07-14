/** 
 * @param workbook
 * @return
 */
public static CellStyle buildDefaultCellStyle(Workbook workbook){
  CellStyle newCellStyle=workbook.createCellStyle();
  Font font=workbook.createFont();
  font.setFontName("??");
  font.setFontHeightInPoints((short)14);
  font.setBold(true);
  newCellStyle.setFont(font);
  newCellStyle.setWrapText(true);
  newCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
  newCellStyle.setAlignment(HorizontalAlignment.CENTER);
  newCellStyle.setLocked(true);
  newCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
  newCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
  newCellStyle.setBorderBottom(BorderStyle.THIN);
  newCellStyle.setBorderLeft(BorderStyle.THIN);
  return newCellStyle;
}

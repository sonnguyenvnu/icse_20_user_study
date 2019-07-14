static protected Serializable extractCell(org.apache.poi.ss.usermodel.Cell cell){
  CellType cellType=cell.getCellType();
  if (cellType.equals(CellType.FORMULA)) {
    cellType=cell.getCachedFormulaResultType();
  }
  if (cellType.equals(CellType.ERROR) || cellType.equals(CellType.BLANK)) {
    return null;
  }
  Serializable value=null;
  if (cellType.equals(CellType.BOOLEAN)) {
    value=cell.getBooleanCellValue();
  }
 else   if (cellType.equals(CellType.NUMERIC)) {
    double d=cell.getNumericCellValue();
    if (HSSFDateUtil.isCellDateFormatted(cell)) {
      value=HSSFDateUtil.getJavaDate(d);
    }
 else {
      value=d;
    }
  }
 else {
    String text=cell.getStringCellValue();
    if (text.length() > 0) {
      value=text;
    }
  }
  return value;
}

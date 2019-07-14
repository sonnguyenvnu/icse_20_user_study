/** 
 * Merge Cells?Indexes are zero-based.
 * @param firstRow Index of first row
 * @param lastRow Index of last row (inclusive), must be equal to or larger than {@code firstRow}
 * @param firstCol Index of first column
 * @param lastCol Index of last column (inclusive), must be equal to or larger than {@code firstCol}
 */
public ExcelWriter merge(int firstRow,int lastRow,int firstCol,int lastCol){
  excelBuilder.merge(firstRow,lastRow,firstCol,lastCol);
  return this;
}

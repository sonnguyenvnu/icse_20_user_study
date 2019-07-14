/** 
 * Calculate all cells that need to be merged
 * @return cells that need to be merged
 */
public List<CellRange> getCellRangeModels(){
  List<CellRange> cellRanges=new ArrayList<CellRange>();
  for (int i=0; i < head.size(); i++) {
    List<String> columnValues=head.get(i);
    for (int j=0; j < columnValues.size(); j++) {
      int lastRow=getLastRangNum(j,columnValues.get(j),columnValues);
      int lastColumn=getLastRangNum(i,columnValues.get(j),getHeadByRowNum(j));
      if ((lastRow > j || lastColumn > i) && lastRow >= 0 && lastColumn >= 0) {
        cellRanges.add(new CellRange(j,lastRow,i,lastColumn));
      }
    }
  }
  return cellRanges;
}

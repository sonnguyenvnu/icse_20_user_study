public static Sheet buildSheetStyle(Sheet currentSheet,Map<Integer,Integer> sheetWidthMap){
  currentSheet.setDefaultColumnWidth(20);
  for (  Map.Entry<Integer,Integer> entry : sheetWidthMap.entrySet()) {
    currentSheet.setColumnWidth(entry.getKey(),entry.getValue());
  }
  return currentSheet;
}

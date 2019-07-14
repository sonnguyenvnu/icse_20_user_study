private void endCellValue(String name) throws SAXException {
  if (curCol >= curRowContent.length) {
    curRowContent=Arrays.copyOf(curRowContent,(int)(curCol * 1.5));
  }
  if (CELL_VALUE_TAG.equals(name)) {
switch (currentCellType) {
case STRING:
      int idx=Integer.parseInt(currentCellValue);
    currentCellValue=new XSSFRichTextString(sst.getEntryAt(idx)).toString();
  currentCellType=FieldType.EMPTY;
break;
}
curRowContent[curCol]=currentCellValue;
}
 else if (CELL_VALUE_TAG_1.equals(name)) {
curRowContent[curCol]=currentCellValue;
}
}

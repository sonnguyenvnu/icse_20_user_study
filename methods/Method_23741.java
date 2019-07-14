/** 
 * Parses a single sheet of XML from this file.
 * @param The XML object for a single worksheet from the ODS file
 */
private void odsParseSheet(XML sheet,boolean header){
  final boolean ignoreTags=true;
  XML[] rows=sheet.getChildren("table:table-row");
  int rowIndex=0;
  for (  XML row : rows) {
    int rowRepeat=row.getInt("table:number-rows-repeated",1);
    boolean rowNotNull=false;
    XML[] cells=row.getChildren();
    int columnIndex=0;
    for (    XML cell : cells) {
      int cellRepeat=cell.getInt("table:number-columns-repeated",1);
      String cellData=ignoreTags ? cell.getString("office:value") : null;
      if (cellData == null) {
        int cellKids=cell.getChildCount();
        if (cellKids != 0) {
          XML[] paragraphElements=cell.getChildren("text:p");
          if (paragraphElements.length != 1) {
            for (            XML el : paragraphElements) {
              System.err.println(el.toString());
            }
            throw new RuntimeException("found more than one text:p element");
          }
          XML textp=paragraphElements[0];
          String textpContent=textp.getContent();
          if (textpContent != null) {
            cellData=textpContent;
          }
 else {
            XML[] textpKids=textp.getChildren();
            StringBuilder cellBuffer=new StringBuilder();
            for (            XML kid : textpKids) {
              String kidName=kid.getName();
              if (kidName == null) {
                odsAppendNotNull(kid,cellBuffer);
              }
 else               if (kidName.equals("text:s")) {
                int spaceCount=kid.getInt("text:c",1);
                for (int space=0; space < spaceCount; space++) {
                  cellBuffer.append(' ');
                }
              }
 else               if (kidName.equals("text:span")) {
                odsAppendNotNull(kid,cellBuffer);
              }
 else               if (kidName.equals("text:a")) {
                if (ignoreTags) {
                  cellBuffer.append(kid.getString("xlink:href"));
                }
 else {
                  odsAppendNotNull(kid,cellBuffer);
                }
              }
 else {
                odsAppendNotNull(kid,cellBuffer);
                System.err.println(getClass().getName() + ": don't understand: " + kid);
              }
            }
            cellData=cellBuffer.toString();
          }
        }
      }
      for (int r=0; r < cellRepeat; r++) {
        if (cellData != null) {
          setString(rowIndex,columnIndex,cellData);
        }
        columnIndex++;
        if (cellData != null) {
          rowNotNull=true;
        }
      }
    }
    if (header) {
      removeTitleRow();
      header=false;
    }
 else {
      if (rowNotNull && rowRepeat > 1) {
        String[] rowStrings=getStringRow(rowIndex);
        for (int r=1; r < rowRepeat; r++) {
          addRow(rowStrings);
        }
      }
      rowIndex+=rowRepeat;
    }
  }
}

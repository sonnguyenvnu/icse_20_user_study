private void handlePreambleAddressCode(byte cc1,byte cc2){
  int row=ROW_INDICES[cc1 & 0x07];
  boolean nextRowDown=(cc2 & 0x20) != 0;
  if (nextRowDown) {
    row++;
  }
  if (row != currentCueBuilder.row) {
    if (captionMode != CC_MODE_ROLL_UP && !currentCueBuilder.isEmpty()) {
      currentCueBuilder=new CueBuilder(captionMode,captionRowCount);
      cueBuilders.add(currentCueBuilder);
    }
    currentCueBuilder.row=row;
  }
  boolean isCursor=(cc2 & 0x10) == 0x10;
  boolean underline=(cc2 & 0x01) == 0x01;
  int cursorOrStyle=(cc2 >> 1) & 0x07;
  currentCueBuilder.setStyle(isCursor ? STYLE_UNCHANGED : cursorOrStyle,underline);
  if (isCursor) {
    currentCueBuilder.indent=COLUMN_INDICES[cursorOrStyle];
  }
}

/** 
 * Block copy lines and associated metadata from one location to another in the circular buffer, taking wraparound into account.
 * @param srcInternal The first line to be copied.
 * @param len         The number of lines to be copied.
 */
private void blockCopyLinesDown(int srcInternal,int len){
  if (len == 0)   return;
  int totalRows=mTotalRows;
  int start=len - 1;
  TerminalRow lineToBeOverWritten=mLines[(srcInternal + start + 1) % totalRows];
  for (int i=start; i >= 0; --i)   mLines[(srcInternal + i + 1) % totalRows]=mLines[(srcInternal + i) % totalRows];
  mLines[(srcInternal) % totalRows]=lineToBeOverWritten;
}

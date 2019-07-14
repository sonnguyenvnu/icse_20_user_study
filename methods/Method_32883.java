/** 
 * @param mouseButton one of the MOUSE_* constants of this class.
 */
public void sendMouseEvent(int mouseButton,int column,int row,boolean pressed){
  if (column < 1)   column=1;
  if (column > mColumns)   column=mColumns;
  if (row < 1)   row=1;
  if (row > mRows)   row=mRows;
  if (mouseButton == MOUSE_LEFT_BUTTON_MOVED && !isDecsetInternalBitSet(DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT)) {
  }
 else   if (isDecsetInternalBitSet(DECSET_BIT_MOUSE_PROTOCOL_SGR)) {
    mSession.write(String.format("\033[<%d;%d;%d" + (pressed ? 'M' : 'm'),mouseButton,column,row));
  }
 else {
    mouseButton=pressed ? mouseButton : 3;
    boolean out_of_bounds=column > 255 - 32 || row > 255 - 32;
    if (!out_of_bounds) {
      byte[] data={'\033','[','M',(byte)(32 + mouseButton),(byte)(32 + column),(byte)(32 + row)};
      mSession.write(data,0,data.length);
    }
  }
}

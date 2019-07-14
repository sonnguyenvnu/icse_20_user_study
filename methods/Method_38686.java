/** 
 * Grows empty text array.
 */
protected void growEmpty(){
  if (textLen >= text.length) {
    int newSize=textLen << 1;
    text=new char[newSize];
  }
}

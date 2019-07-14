/** 
 * Generic dilate/erode filter using luminance values as decision factor. [toxi 050728]
 */
protected void dilate(){
  int index=0;
  int maxIndex=pixels.length;
  int[] outgoing=new int[maxIndex];
  while (index < maxIndex) {
    int curRowIndex=index;
    int maxRowIndex=index + pixelWidth;
    while (index < maxRowIndex) {
      int orig=pixels[index];
      int result=orig;
      int idxLeft=index - 1;
      int idxRight=index + 1;
      int idxUp=index - pixelWidth;
      int idxDown=index + pixelWidth;
      if (idxLeft < curRowIndex) {
        idxLeft=index;
      }
      if (idxRight >= maxRowIndex) {
        idxRight=index;
      }
      if (idxUp < 0) {
        idxUp=index;
      }
      if (idxDown >= maxIndex) {
        idxDown=index;
      }
      int colUp=pixels[idxUp];
      int colLeft=pixels[idxLeft];
      int colDown=pixels[idxDown];
      int colRight=pixels[idxRight];
      int currLum=77 * (orig >> 16 & 0xff) + 151 * (orig >> 8 & 0xff) + 28 * (orig & 0xff);
      int lumLeft=77 * (colLeft >> 16 & 0xff) + 151 * (colLeft >> 8 & 0xff) + 28 * (colLeft & 0xff);
      int lumRight=77 * (colRight >> 16 & 0xff) + 151 * (colRight >> 8 & 0xff) + 28 * (colRight & 0xff);
      int lumUp=77 * (colUp >> 16 & 0xff) + 151 * (colUp >> 8 & 0xff) + 28 * (colUp & 0xff);
      int lumDown=77 * (colDown >> 16 & 0xff) + 151 * (colDown >> 8 & 0xff) + 28 * (colDown & 0xff);
      if (lumLeft > currLum) {
        result=colLeft;
        currLum=lumLeft;
      }
      if (lumRight > currLum) {
        result=colRight;
        currLum=lumRight;
      }
      if (lumUp > currLum) {
        result=colUp;
        currLum=lumUp;
      }
      if (lumDown > currLum) {
        result=colDown;
        currLum=lumDown;
      }
      outgoing[index++]=result;
    }
  }
  System.arraycopy(outgoing,0,pixels,0,maxIndex);
}

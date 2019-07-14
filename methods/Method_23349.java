/** 
 * Emit a sentence of text, defined as a chunk of text without any newlines.
 * @param stop non-inclusive, the end of the text in question
 * @return false if cannot fit
 */
protected boolean textSentence(char[] buffer,int start,int stop,float boxWidth,float spaceWidth){
  float runningX=0;
  int lineStart=start;
  int wordStart=start;
  int index=start;
  while (index <= stop) {
    if ((buffer[index] == ' ') || (index == stop)) {
      float wordWidth=0;
      if (index > wordStart) {
        wordWidth=textWidthImpl(buffer,wordStart,index);
      }
      if (runningX + wordWidth >= boxWidth) {
        if (runningX != 0) {
          index=wordStart;
          textSentenceBreak(lineStart,index);
          while ((index < stop) && (buffer[index] == ' ')) {
            index++;
          }
        }
 else {
          if (index - wordStart < 25) {
            do {
              index--;
              if (index == wordStart) {
                return false;
              }
              wordWidth=textWidthImpl(buffer,wordStart,index);
            }
 while (wordWidth > boxWidth);
          }
 else {
            int lastIndex=index;
            index=wordStart + 1;
            while ((wordWidth=textWidthImpl(buffer,wordStart,index)) < boxWidth) {
              index++;
              if (index > lastIndex) {
                break;
              }
            }
            index--;
            if (index == wordStart) {
              return false;
            }
          }
          textSentenceBreak(lineStart,index);
        }
        lineStart=index;
        wordStart=index;
        runningX=0;
      }
 else       if (index == stop) {
        textSentenceBreak(lineStart,index);
        index++;
      }
 else {
        runningX+=wordWidth;
        wordStart=index;
        index++;
      }
    }
 else {
      index++;
    }
  }
  return true;
}

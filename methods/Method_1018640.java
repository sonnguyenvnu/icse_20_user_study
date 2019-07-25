/** 
 * <p>This method attempts to find the bottom-right alignment pattern in the image. It is a bit messy since it's pretty performance-critical and so is written to be fast foremost.</p>
 * @return {@link AlignmentPattern} if found
 * @throws NotFoundException if not found
 */
AlignmentPattern find() throws NotFoundException {
  int startX=this.startX;
  int height=this.height;
  int maxJ=startX + width;
  int middleI=startY + (height / 2);
  int[] stateCount=new int[3];
  for (int iGen=0; iGen < height; iGen++) {
    int i=middleI + ((iGen & 0x01) == 0 ? (iGen + 1) / 2 : -((iGen + 1) / 2));
    stateCount[0]=0;
    stateCount[1]=0;
    stateCount[2]=0;
    int j=startX;
    while (j < maxJ && !image.get(j,i)) {
      j++;
    }
    int currentState=0;
    while (j < maxJ) {
      if (image.get(j,i)) {
        if (currentState == 1) {
          stateCount[1]++;
        }
 else {
          if (currentState == 2) {
            if (foundPatternCross(stateCount)) {
              AlignmentPattern confirmed=handlePossibleCenter(stateCount,i,j);
              if (confirmed != null) {
                return confirmed;
              }
            }
            stateCount[0]=stateCount[2];
            stateCount[1]=1;
            stateCount[2]=0;
            currentState=1;
          }
 else {
            stateCount[++currentState]++;
          }
        }
      }
 else {
        if (currentState == 1) {
          currentState++;
        }
        stateCount[currentState]++;
      }
      j++;
    }
    if (foundPatternCross(stateCount)) {
      AlignmentPattern confirmed=handlePossibleCenter(stateCount,i,maxJ);
      if (confirmed != null) {
        return confirmed;
      }
    }
  }
  if (!possibleCenters.isEmpty()) {
    return possibleCenters.get(0);
  }
  throw NotFoundException.getNotFoundInstance();
}

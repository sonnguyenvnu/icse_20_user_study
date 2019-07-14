/** 
 * Unselected dots can be in 6 states: #1 At rest #2 Joining neighbour, still separate #3 Joining neighbour, combined curved #4 Joining neighbour, combined straight #5 Join retreating #6 Dot re-showing / revealing It can also be in a combination of these states e.g. joining one neighbour while retreating from another.  We therefore create a Path so that we can examine each dot pair separately and later take the union for these cases. This function returns a path for the given dot **and any action to it's right** e.g. joining or retreating from it's neighbour
 */
private Path getUnselectedPath(int page,float centerX,float nextCenterX,float joiningFraction,float dotRevealFraction){
  unselectedDotPath.rewind();
  if ((joiningFraction == 0f || joiningFraction == INVALID_FRACTION) && dotRevealFraction == 0f && !(page == currentPage && selectedDotInPosition)) {
    unselectedDotPath.addCircle(dotCenterX[page],dotCenterY,dotRadius,Path.Direction.CW);
  }
  if (joiningFraction > 0f && joiningFraction <= 0.5f && retreatingJoinX1 == INVALID_FRACTION) {
    unselectedDotLeftPath.rewind();
    unselectedDotLeftPath.moveTo(centerX,dotBottomY);
    rectF.set(centerX - dotRadius,dotTopY,centerX + dotRadius,dotBottomY);
    unselectedDotLeftPath.arcTo(rectF,90,180,true);
    endX1=centerX + dotRadius + (joiningFraction * gap);
    endY1=dotCenterY;
    controlX1=centerX + halfDotRadius;
    controlY1=dotTopY;
    controlX2=endX1;
    controlY2=endY1 - halfDotRadius;
    unselectedDotLeftPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX1,endY1);
    endX2=centerX;
    endY2=dotBottomY;
    controlX1=endX1;
    controlY1=endY1 + halfDotRadius;
    controlX2=centerX + halfDotRadius;
    controlY2=dotBottomY;
    unselectedDotLeftPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX2,endY2);
    unselectedDotPath.op(unselectedDotLeftPath,Path.Op.UNION);
    unselectedDotRightPath.rewind();
    unselectedDotRightPath.moveTo(nextCenterX,dotBottomY);
    rectF.set(nextCenterX - dotRadius,dotTopY,nextCenterX + dotRadius,dotBottomY);
    unselectedDotRightPath.arcTo(rectF,90,-180,true);
    endX1=nextCenterX - dotRadius - (joiningFraction * gap);
    endY1=dotCenterY;
    controlX1=nextCenterX - halfDotRadius;
    controlY1=dotTopY;
    controlX2=endX1;
    controlY2=endY1 - halfDotRadius;
    unselectedDotRightPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX1,endY1);
    endX2=nextCenterX;
    endY2=dotBottomY;
    controlX1=endX1;
    controlY1=endY1 + halfDotRadius;
    controlX2=endX2 - halfDotRadius;
    controlY2=dotBottomY;
    unselectedDotRightPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX2,endY2);
    unselectedDotPath.op(unselectedDotRightPath,Path.Op.UNION);
  }
  if (joiningFraction > 0.5f && joiningFraction < 1f && retreatingJoinX1 == INVALID_FRACTION) {
    float adjustedFraction=(joiningFraction - 0.2f) * 1.25f;
    unselectedDotPath.moveTo(centerX,dotBottomY);
    rectF.set(centerX - dotRadius,dotTopY,centerX + dotRadius,dotBottomY);
    unselectedDotPath.arcTo(rectF,90,180,true);
    endX1=centerX + dotRadius + (gap / 2);
    endY1=dotCenterY - (adjustedFraction * dotRadius);
    controlX1=endX1 - (adjustedFraction * dotRadius);
    controlY1=dotTopY;
    controlX2=endX1 - ((1 - adjustedFraction) * dotRadius);
    controlY2=endY1;
    unselectedDotPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX1,endY1);
    endX2=nextCenterX;
    endY2=dotTopY;
    controlX1=endX1 + ((1 - adjustedFraction) * dotRadius);
    controlY1=endY1;
    controlX2=endX1 + (adjustedFraction * dotRadius);
    controlY2=dotTopY;
    unselectedDotPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX2,endY2);
    rectF.set(nextCenterX - dotRadius,dotTopY,nextCenterX + dotRadius,dotBottomY);
    unselectedDotPath.arcTo(rectF,270,180,true);
    endY1=dotCenterY + (adjustedFraction * dotRadius);
    controlX1=endX1 + (adjustedFraction * dotRadius);
    controlY1=dotBottomY;
    controlX2=endX1 + ((1 - adjustedFraction) * dotRadius);
    controlY2=endY1;
    unselectedDotPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX1,endY1);
    endX2=centerX;
    endY2=dotBottomY;
    controlX1=endX1 - ((1 - adjustedFraction) * dotRadius);
    controlY1=endY1;
    controlX2=endX1 - (adjustedFraction * dotRadius);
    controlY2=endY2;
    unselectedDotPath.cubicTo(controlX1,controlY1,controlX2,controlY2,endX2,endY2);
  }
  if (joiningFraction == 1 && retreatingJoinX1 == INVALID_FRACTION) {
    rectF.set(centerX - dotRadius,dotTopY,nextCenterX + dotRadius,dotBottomY);
    unselectedDotPath.addRoundRect(rectF,dotRadius,dotRadius,Path.Direction.CW);
  }
  if (dotRevealFraction > MINIMAL_REVEAL) {
    unselectedDotPath.addCircle(centerX,dotCenterY,dotRevealFraction * dotRadius,Path.Direction.CW);
  }
  return unselectedDotPath;
}

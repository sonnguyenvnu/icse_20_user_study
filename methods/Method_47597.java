public int getDegreesFromCoords(float pointX,float pointY,boolean forceLegal,final Boolean[] isInnerCircle){
  if (!mDrawValuesReady) {
    return -1;
  }
  double hypotenuse=Math.sqrt((pointY - mYCenter) * (pointY - mYCenter) + (pointX - mXCenter) * (pointX - mXCenter));
  if (mHasInnerCircle) {
    if (forceLegal) {
      int innerNumberRadius=(int)(mCircleRadius * mInnerNumbersRadiusMultiplier);
      int distanceToInnerNumber=(int)Math.abs(hypotenuse - innerNumberRadius);
      int outerNumberRadius=(int)(mCircleRadius * mOuterNumbersRadiusMultiplier);
      int distanceToOuterNumber=(int)Math.abs(hypotenuse - outerNumberRadius);
      isInnerCircle[0]=(distanceToInnerNumber <= distanceToOuterNumber);
    }
 else {
      int minAllowedHypotenuseForInnerNumber=(int)(mCircleRadius * mInnerNumbersRadiusMultiplier) - mSelectionRadius;
      int maxAllowedHypotenuseForOuterNumber=(int)(mCircleRadius * mOuterNumbersRadiusMultiplier) + mSelectionRadius;
      int halfwayHypotenusePoint=(int)(mCircleRadius * ((mOuterNumbersRadiusMultiplier + mInnerNumbersRadiusMultiplier) / 2));
      if (hypotenuse >= minAllowedHypotenuseForInnerNumber && hypotenuse <= halfwayHypotenusePoint) {
        isInnerCircle[0]=true;
      }
 else       if (hypotenuse <= maxAllowedHypotenuseForOuterNumber && hypotenuse >= halfwayHypotenusePoint) {
        isInnerCircle[0]=false;
      }
 else {
        return -1;
      }
    }
  }
 else {
    if (!forceLegal) {
      int distanceToNumber=(int)Math.abs(hypotenuse - mLineLength);
      int maxAllowedDistance=(int)(mCircleRadius * (1 - mNumbersRadiusMultiplier));
      if (distanceToNumber > maxAllowedDistance) {
        return -1;
      }
    }
  }
  float opposite=Math.abs(pointY - mYCenter);
  double radians=Math.asin(opposite / hypotenuse);
  int degrees=(int)(radians * 180 / Math.PI);
  boolean rightSide=(pointX > mXCenter);
  boolean topSide=(pointY < mYCenter);
  if (rightSide && topSide) {
    degrees=90 - degrees;
  }
 else   if (rightSide && !topSide) {
    degrees=90 + degrees;
  }
 else   if (!rightSide && !topSide) {
    degrees=270 - degrees;
  }
 else   if (!rightSide && topSide) {
    degrees=270 + degrees;
  }
  return degrees;
}

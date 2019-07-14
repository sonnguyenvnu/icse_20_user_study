private boolean didChildRangeChange(int minPositionPreLayout,int maxPositionPreLayout){
  findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
  return mMinMaxLayoutPositions[0] != minPositionPreLayout || mMinMaxLayoutPositions[1] != maxPositionPreLayout;
}

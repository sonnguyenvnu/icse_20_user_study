public static boolean isMeasureSpecCompatible(int oldSizeSpec,int sizeSpec,int oldMeasuredSize){
  final int newSpecMode=SizeSpec.getMode(sizeSpec);
  final int newSpecSize=SizeSpec.getSize(sizeSpec);
  final int oldSpecMode=SizeSpec.getMode(oldSizeSpec);
  final int oldSpecSize=SizeSpec.getSize(oldSizeSpec);
  return oldSizeSpec == sizeSpec || (oldSpecMode == UNSPECIFIED && newSpecMode == UNSPECIFIED) || newSizeIsExactAndMatchesOldMeasuredSize(newSpecMode,newSpecSize,oldMeasuredSize) || oldSizeIsUnspecifiedAndStillFits(oldSpecMode,newSpecMode,newSpecSize,oldMeasuredSize) || newMeasureSizeIsStricterAndStillValid(oldSpecMode,newSpecMode,oldSpecSize,newSpecSize,oldMeasuredSize);
}

/** 
 * Check if a cached nested tree has compatible SizeSpec to be reused as is or if it needs to be recomputed. The conditions to be able to re-use previous measurements are: 1) The measureSpec is the same 2) The new measureSpec is EXACTLY and the last measured size matches the measureSpec size. 3) The old measureSpec is UNSPECIFIED, the new one is AT_MOST and the old measured size is smaller that the maximum size the new measureSpec will allow. 4) Both measure specs are AT_MOST. The old measure spec allows a bigger size than the new and the old measured size is smaller than the allowed max size for the new sizeSpec.
 */
public static boolean hasCompatibleSizeSpec(int oldWidthSpec,int oldHeightSpec,int newWidthSpec,int newHeightSpec,float oldMeasuredWidth,float oldMeasuredHeight){
  final boolean widthIsCompatible=MeasureComparisonUtils.isMeasureSpecCompatible(oldWidthSpec,newWidthSpec,(int)oldMeasuredWidth);
  final boolean heightIsCompatible=MeasureComparisonUtils.isMeasureSpecCompatible(oldHeightSpec,newHeightSpec,(int)oldMeasuredHeight);
  return widthIsCompatible && heightIsCompatible;
}

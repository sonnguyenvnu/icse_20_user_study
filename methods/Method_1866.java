/** 
 * This method calculate the ratio in case the downsampling was enabled
 * @param downsampleRatio The ratio from downsampling
 * @return The ratio to use for software resize using the downsampling limitation
 */
@VisibleForTesting public static int calculateDownsampleNumerator(int downsampleRatio){
  return Math.max(1,SCALE_DENOMINATOR / downsampleRatio);
}

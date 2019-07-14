/** 
 * Fills missing values in the given  {@code bitrates} array by calculates an estimation using theclosest reference bitrate value.
 * @param bitrates An array of bitrates to be filled with estimations. Missing values are set to{@link Format#NO_VALUE}.
 * @param formats An array of formats, one for each bitrate.
 * @param referenceBitrates An array of reference bitrates which are used to calculateestimations.
 * @param referenceBitrateRatios An array containing ratio of reference bitrates to their bitrateestimates.
 */
private static void estimateBitrates(int[] bitrates,Format[] formats,int[] referenceBitrates,float[] referenceBitrateRatios){
  for (int i=0; i < bitrates.length; i++) {
    if (bitrates[i] == Format.NO_VALUE) {
      int formatBitrate=formats[i].bitrate;
      if (formatBitrate != Format.NO_VALUE) {
        int closestReferenceBitrateIndex=getClosestBitrateIndex(formatBitrate,referenceBitrates);
        bitrates[i]=(int)(referenceBitrateRatios[closestReferenceBitrateIndex] * formatBitrate);
      }
    }
  }
}

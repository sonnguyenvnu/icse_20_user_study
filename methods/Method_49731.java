/** 
 * Compute the latitude precision value for a given code length. Lengths <= 10 have the same precision for latitude and longitude, but lengths > 10 have different precisions due to the grid method having fewer columns than rows. Copied from the JS implementation.
 */
private static double computeLatitudePrecision(int codeLength){
  if (codeLength <= CODE_PRECISION_NORMAL) {
    return Math.pow(ENCODING_BASE,(double)(codeLength / -2 + 2));
  }
  return Math.pow(ENCODING_BASE,-3) / Math.pow(GRID_ROWS,codeLength - PAIR_CODE_LENGTH);
}

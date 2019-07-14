/** 
 * Returns whether to work around problems with passthrough audio tracks. See [Internal: b/18899620, b/19187573, b/21145353].
 */
private static boolean needsPassthroughWorkarounds(@C.Encoding int outputEncoding){
  return Util.SDK_INT < 23 && (outputEncoding == C.ENCODING_AC3 || outputEncoding == C.ENCODING_E_AC3);
}

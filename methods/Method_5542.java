/** 
 * Builds binary CEA-708 initialization data.
 * @param isWideAspectRatio Whether the closed caption service is formatted for displays with 16:9aspect ratio.
 * @return Binary CEA-708 initializaton data.
 */
public static List<byte[]> buildData(boolean isWideAspectRatio){
  return Collections.singletonList(new byte[]{(byte)(isWideAspectRatio ? 1 : 0)});
}

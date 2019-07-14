/** 
 * The profile levels supported by the decoder.
 * @return The profile levels supported by the decoder.
 */
public CodecProfileLevel[] getProfileLevels(){
  return capabilities == null || capabilities.profileLevels == null ? new CodecProfileLevel[0] : capabilities.profileLevels;
}

/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + B3CameraImageData.M_RGBCOLORDATA));
  check(memGetAddress(struct + B3CameraImageData.M_DEPTHVALUES));
  check(memGetAddress(struct + B3CameraImageData.M_SEGMENTATIONMASKVALUES));
}

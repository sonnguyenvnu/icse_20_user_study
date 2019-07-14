/** 
 * Get all default formats supported by Fresco. Does not include  {@link ImageFormat#UNKNOWN}.
 * @return all supported default formats
 */
public static List<ImageFormat> getDefaultFormats(){
  if (sAllDefaultFormats == null) {
    List<ImageFormat> mDefaultFormats=new ArrayList<>(9);
    mDefaultFormats.add(JPEG);
    mDefaultFormats.add(PNG);
    mDefaultFormats.add(GIF);
    mDefaultFormats.add(BMP);
    mDefaultFormats.add(ICO);
    mDefaultFormats.add(WEBP_SIMPLE);
    mDefaultFormats.add(WEBP_LOSSLESS);
    mDefaultFormats.add(WEBP_EXTENDED);
    mDefaultFormats.add(WEBP_EXTENDED_WITH_ALPHA);
    mDefaultFormats.add(WEBP_ANIMATED);
    mDefaultFormats.add(HEIF);
    sAllDefaultFormats=ImmutableList.copyOf(mDefaultFormats);
  }
  return sAllDefaultFormats;
}

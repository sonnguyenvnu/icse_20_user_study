/** 
 * Converts and scales a image according to the given {@link DLNAImageProfile}. Preserves aspect ratio. Format support is limited to that of  {@link ImageIO}.
 * @param outputProfile the DLNA media profile to adhere to for the output.
 * @param padToSize Whether padding should be used if source aspect doesn'tmatch target aspect.
 * @param filterChain a {@link BufferedImageFilterChain} to apply during theoperation or  {@code null}.
 * @return The scaled and/or converted image, {@code null} if thesource is  {@code null}.
 * @exception IOException if the operation fails.
 */
public DLNAImageInputStream transcode(DLNAImageProfile outputProfile,boolean padToSize,BufferedImageFilterChain filterChain) throws IOException {
  DLNAImage image;
  image=(DLNAImage)ImagesUtil.transcodeImage(this.getBytes(false),outputProfile,false,padToSize,filterChain);
  return image != null ? new DLNAImageInputStream(image) : null;
}

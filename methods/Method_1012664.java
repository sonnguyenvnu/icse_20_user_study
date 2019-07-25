/** 
 * Converts and scales a thumbnail according to the given {@link DLNAImageProfile}. Preserves aspect ratio. Format support is limited to that of  {@link ImageIO}.
 * @param outputProfile the DLNA media profile to adhere to for the output.
 * @param padToSize Whether padding should be used if source aspect doesn'tmatch target aspect.
 * @param filterChain a {@link BufferedImageFilterChain} to apply during theoperation or  {@code null}.
 * @return The scaled and/or converted thumbnail, {@code null} if thesource is  {@code null}.
 * @exception IOException if the operation fails.
 */
public DLNAThumbnailInputStream transcode(DLNAImageProfile outputProfile,boolean padToSize,BufferedImageFilterChain filterChain) throws IOException {
  DLNAThumbnail thumbnail;
  thumbnail=(DLNAThumbnail)ImagesUtil.transcodeImage(this.getBytes(false),outputProfile,true,padToSize,filterChain);
  return thumbnail != null ? new DLNAThumbnailInputStream(thumbnail) : null;
}

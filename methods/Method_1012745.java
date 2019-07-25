/** 
 * Converts and scales the  {@link Image} according to the given{@link DLNAImageProfile}. Preserves aspect ratio and rotates/flips the image according to Exif orientation. Format support is limited to that of {@link ImageIO}.
 * @param outputProfile the {@link DLNAImageProfile} to convert to. Thisoverrides  {@code outputFormat}.
 * @param dlnaCompliant whether or not the output image should be restrictedto DLNA compliance. This also means that the output can be safely cast to  {@link DLNAImage}.
 * @param dlnaThumbnail whether or not the output image should be restrictedto DLNA thumbnail compliance. This also means that the output can be safely cast to  {@link DLNAThumbnail}.
 * @param padToSize whether padding should be used if source aspect doesn'tmatch target aspect.
 * @param filterChain a {@link BufferedImageFilterChain} to apply during theoperation or  {@code null}.
 * @return The scaled and/or converted {@link Image} or {@code null} if thesource is  {@code null}.
 * @throws IOException if the operation fails.
 */
public Image transcode(DLNAImageProfile outputProfile,boolean dlnaCompliant,boolean dlnaThumbnail,boolean padToSize,BufferedImageFilterChain filterChain) throws IOException {
  return ImagesUtil.transcodeImage(this.getBytes(false),0,0,ScaleType.MAX,outputProfile,dlnaCompliant,dlnaThumbnail,padToSize,filterChain);
}

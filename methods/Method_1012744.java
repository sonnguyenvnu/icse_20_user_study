/** 
 * Converts and scales the  {@link Image}. Preserves aspect ratio and rotates/flips the image according to Exif orientation. Format support is limited to that of  {@link ImageIO}.
 * @param width the new width or 0 to disable scaling.
 * @param height the new height or 0 to disable scaling.
 * @param scaleType the {@link ScaleType} to use when scaling.
 * @param outputFormat the {@link ImageFormat} to convert to or{@link ImageFormat#SOURCE} to preserve source format.Overridden by  {@code outputProfile}.
 * @param dlnaCompliant whether or not the output image should be restrictedto DLNA compliance. This also means that the output can be safely cast to  {@link DLNAImage}.
 * @param dlnaThumbnail whether or not the output image should be restrictedto DLNA thumbnail compliance. This also means that the output can be safely cast to  {@link DLNAThumbnail}.
 * @param padToSize whether padding should be used if source aspect doesn'tmatch target aspect.
 * @param filterChain a {@link BufferedImageFilterChain} to apply during theoperation or  {@code null}.
 * @return The scaled and/or converted {@link Image} or {@code null} if thesource is  {@code null}.
 * @throws IOException if the operation fails.
 */
public Image transcode(int width,int height,ScaleType scaleType,ImageFormat outputFormat,boolean dlnaCompliant,boolean dlnaThumbnail,boolean padToSize,BufferedImageFilterChain filterChain) throws IOException {
  return ImagesUtil.transcodeImage(this.getBytes(false),width,height,scaleType,outputFormat,dlnaCompliant,dlnaThumbnail,padToSize,filterChain);
}

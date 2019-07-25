/** 
 * Converts the  {@link Image}. Preserves aspect ratio and rotates/flips the image according to Exif orientation. Format support is limited to that of {@link ImageIO}.
 * @param outputFormat the {@link ImageFormat} to convert to or{@link ImageFormat#SOURCE} to preserve source format.Overridden by  {@code outputProfile}.
 * @param dlnaCompliant whether or not the output image should be restrictedto DLNA compliance. This also means that the output can be safely cast to  {@link DLNAImage}.
 * @param dlnaThumbnail whether or not the output image should be restrictedto DLNA thumbnail compliance. This also means that the output can be safely cast to  {@link DLNAThumbnail}.
 * @param filterChain a {@link BufferedImageFilterChain} to apply during theoperation or  {@code null}.
 * @return The converted {@link Image} or {@code null} if the source is{@code null}.
 * @throws IOException if the operation fails.
 */
public Image transcode(ImageFormat outputFormat,boolean dlnaCompliant,boolean dlnaThumbnail,BufferedImageFilterChain filterChain) throws IOException {
  return transcode(0,0,null,outputFormat,dlnaCompliant,dlnaThumbnail,false,filterChain);
}

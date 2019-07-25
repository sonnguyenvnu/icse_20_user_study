/** 
 * Converts and scales the image according to the given {@link DLNAImageProfile}. Preserves aspect ratio. Format support is limited to that of  {@link ImageIO}.
 * @param outputProfile the {@link DLNAImageProfile} to adhere to for theoutput.
 * @param dlnaThumbnail whether or not the output image should berestricted to DLNA thumbnail compliance. This also means that the output can be safely cast to {@link DLNAThumbnail}.
 * @param padToSize Whether padding should be used if source aspect doesn'tmatch target aspect.
 * @return The scaled and/or converted thumbnail, {@code null} if thesource is  {@code null}.
 * @exception IOException if the operation fails.
 */
public DLNAImage transcode(DLNAImageProfile outputProfile,boolean dlnaThumbnail,boolean padToSize) throws IOException {
  return (DLNAImage)ImagesUtil.transcodeImage(this,outputProfile,dlnaThumbnail,padToSize,null);
}

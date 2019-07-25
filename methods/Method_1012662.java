/** 
 * Converts and scales the thumbnail according to the given {@link DLNAImageProfile}. Preserves aspect ratio. Format support is limited to that of  {@link ImageIO}.
 * @param outputProfile the {@link DLNAImageProfile} to adhere to for the output.
 * @param padToSize Whether padding should be used if source aspect doesn'tmatch target aspect.
 * @return The scaled and/or converted thumbnail, {@code null} if thesource is  {@code null}.
 * @exception IOException if the operation fails.
 */
public DLNAThumbnail transcode(DLNAImageProfile outputProfile,boolean padToSize) throws IOException {
  return (DLNAThumbnail)ImagesUtil.transcodeImage(this,outputProfile,true,padToSize,null);
}

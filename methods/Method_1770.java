/** 
 * Tries to match imageHeaderByte and headerSize against every known image format. If any match succeeds, corresponding ImageFormat is returned.
 * @param headerBytes the header bytes to check
 * @param headerSize the available header size
 * @return ImageFormat for given imageHeaderBytes or UNKNOWN if no such type could be recognized
 */
@Nullable @Override public final ImageFormat determineFormat(byte[] headerBytes,int headerSize){
  Preconditions.checkNotNull(headerBytes);
  if (WebpSupportStatus.isWebpHeader(headerBytes,0,headerSize)) {
    return getWebpFormat(headerBytes,headerSize);
  }
  if (isJpegHeader(headerBytes,headerSize)) {
    return DefaultImageFormats.JPEG;
  }
  if (isPngHeader(headerBytes,headerSize)) {
    return DefaultImageFormats.PNG;
  }
  if (isGifHeader(headerBytes,headerSize)) {
    return DefaultImageFormats.GIF;
  }
  if (isBmpHeader(headerBytes,headerSize)) {
    return DefaultImageFormats.BMP;
  }
  if (isIcoHeader(headerBytes,headerSize)) {
    return DefaultImageFormats.ICO;
  }
  if (isHeifHeader(headerBytes,headerSize)) {
    return DefaultImageFormats.HEIF;
  }
  return ImageFormat.UNKNOWN;
}

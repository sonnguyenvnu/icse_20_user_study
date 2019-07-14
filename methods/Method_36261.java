/** 
 * For GIF, we only need 3 bytes, 'GIF', For WebP, we need 12 bytes, 'RIFF' + size + 'WEBP', to determine still/animated WebP, we need 5 extra bytes, 4 bytes chunk header to check for extended WebP format, 1 byte to check for animated bit. reference: https://developers.google.com/speed/webp/docs/riff_container
 */
public static int getImageType(File file){
  int type=TYPE_STILL_IMAGE;
  try {
    FileInputStream inputStream=new FileInputStream(file);
    byte[] header=new byte[20];
    int read=inputStream.read(header);
    if (read >= 3 && isGifHeader(header)) {
      type=TYPE_GIF;
    }
 else     if (read >= 12 && isWebpHeader(header)) {
      if (read >= 17 && isExtendedWebp(header) && (header[16] & ANIMATED_WEBP_MASK) != 0) {
        type=TYPE_ANIMATED_WEBP;
      }
 else {
        type=TYPE_STILL_WEBP;
      }
    }
    inputStream.close();
  }
 catch (  IOException e) {
  }
  return type;
}

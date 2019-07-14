/** 
 * Verifies whether the next bytes in  {@code header} are a vorbis header of the given{@code headerType}.
 * @param headerType the type of the header expected.
 * @param header the alleged header bytes.
 * @param quiet if {@code true} no exceptions are thrown. Instead {@code false} is returned.
 * @return the number of bytes read.
 * @throws ParserException thrown if header type or capture pattern is not as expected.
 */
public static boolean verifyVorbisHeaderCapturePattern(int headerType,ParsableByteArray header,boolean quiet) throws ParserException {
  if (header.bytesLeft() < 7) {
    if (quiet) {
      return false;
    }
 else {
      throw new ParserException("too short header: " + header.bytesLeft());
    }
  }
  if (header.readUnsignedByte() != headerType) {
    if (quiet) {
      return false;
    }
 else {
      throw new ParserException("expected header type " + Integer.toHexString(headerType));
    }
  }
  if (!(header.readUnsignedByte() == 'v' && header.readUnsignedByte() == 'o' && header.readUnsignedByte() == 'r' && header.readUnsignedByte() == 'b' && header.readUnsignedByte() == 'i' && header.readUnsignedByte() == 's')) {
    if (quiet) {
      return false;
    }
 else {
      throw new ParserException("expected characters 'vorbis'");
    }
  }
  return true;
}

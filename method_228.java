/** 
 * Reads a Jpeg image, removes all XMP XML (by removing the APP1 segment), and writes the result to a stream. <p>
 * @param byteSource ByteSource containing Jpeg image data.
 * @param os OutputStream to write the image to.
 */
public void _XXXXX_(final ByteSource byteSource,final OutputStream os) throws ImageReadException, IOException {
  final JFIFPieces jfifPieces=analyzeJFIF(byteSource);
  List<JFIFPiece> pieces=jfifPieces.pieces;
  pieces=removeXmpSegments(pieces);
  writeSegments(os,pieces);
}
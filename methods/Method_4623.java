/** 
 * Parses an hdlr atom.
 * @param hdlr The hdlr atom to decode.
 * @return The handler value.
 */
private static int parseHdlr(ParsableByteArray hdlr){
  hdlr.setPosition(Atom.FULL_HEADER_SIZE + 4);
  return hdlr.readInt();
}

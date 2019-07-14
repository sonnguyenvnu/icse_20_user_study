/** 
 * Process an ftyp atom to determine whether the media is QuickTime.
 * @param atomData The ftyp atom data.
 * @return Whether the media is QuickTime.
 */
private static boolean processFtypAtom(ParsableByteArray atomData){
  atomData.setPosition(Atom.HEADER_SIZE);
  int majorBrand=atomData.readInt();
  if (majorBrand == BRAND_QUICKTIME) {
    return true;
  }
  atomData.skipBytes(4);
  while (atomData.bytesLeft() > 0) {
    if (atomData.readInt() == BRAND_QUICKTIME) {
      return true;
    }
  }
  return false;
}

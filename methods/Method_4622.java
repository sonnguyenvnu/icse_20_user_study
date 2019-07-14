/** 
 * Parses a tkhd atom (defined in 14496-12).
 * @return An object containing the parsed data.
 */
private static TkhdData parseTkhd(ParsableByteArray tkhd){
  tkhd.setPosition(Atom.HEADER_SIZE);
  int fullAtom=tkhd.readInt();
  int version=Atom.parseFullAtomVersion(fullAtom);
  tkhd.skipBytes(version == 0 ? 8 : 16);
  int trackId=tkhd.readInt();
  tkhd.skipBytes(4);
  boolean durationUnknown=true;
  int durationPosition=tkhd.getPosition();
  int durationByteCount=version == 0 ? 4 : 8;
  for (int i=0; i < durationByteCount; i++) {
    if (tkhd.data[durationPosition + i] != -1) {
      durationUnknown=false;
      break;
    }
  }
  long duration;
  if (durationUnknown) {
    tkhd.skipBytes(durationByteCount);
    duration=C.TIME_UNSET;
  }
 else {
    duration=version == 0 ? tkhd.readUnsignedInt() : tkhd.readUnsignedLongToLong();
    if (duration == 0) {
      duration=C.TIME_UNSET;
    }
  }
  tkhd.skipBytes(16);
  int a00=tkhd.readInt();
  int a01=tkhd.readInt();
  tkhd.skipBytes(4);
  int a10=tkhd.readInt();
  int a11=tkhd.readInt();
  int rotationDegrees;
  int fixedOne=65536;
  if (a00 == 0 && a01 == fixedOne && a10 == -fixedOne && a11 == 0) {
    rotationDegrees=90;
  }
 else   if (a00 == 0 && a01 == -fixedOne && a10 == fixedOne && a11 == 0) {
    rotationDegrees=270;
  }
 else   if (a00 == -fixedOne && a01 == 0 && a10 == 0 && a11 == -fixedOne) {
    rotationDegrees=180;
  }
 else {
    rotationDegrees=0;
  }
  return new TkhdData(trackId,duration,rotationDegrees);
}

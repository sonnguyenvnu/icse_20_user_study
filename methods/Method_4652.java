/** 
 * Processes the atom payload. If  {@link #atomData} is null and the size is at or above thethreshold  {@link #RELOAD_MINIMUM_SEEK_DISTANCE},  {@code true} is returned and the caller shouldrestart loading at the position in  {@code positionHolder}. Otherwise, the atom is read/skipped.
 */
private boolean readAtomPayload(ExtractorInput input,PositionHolder positionHolder) throws IOException, InterruptedException {
  long atomPayloadSize=atomSize - atomHeaderBytesRead;
  long atomEndPosition=input.getPosition() + atomPayloadSize;
  boolean seekRequired=false;
  if (atomData != null) {
    input.readFully(atomData.data,atomHeaderBytesRead,(int)atomPayloadSize);
    if (atomType == Atom.TYPE_ftyp) {
      isQuickTime=processFtypAtom(atomData);
    }
 else     if (!containerAtoms.isEmpty()) {
      containerAtoms.peek().add(new Atom.LeafAtom(atomType,atomData));
    }
  }
 else {
    if (atomPayloadSize < RELOAD_MINIMUM_SEEK_DISTANCE) {
      input.skipFully((int)atomPayloadSize);
    }
 else {
      positionHolder.position=input.getPosition() + atomPayloadSize;
      seekRequired=true;
    }
  }
  processAtomEnded(atomEndPosition);
  return seekRequired && parserState != STATE_READING_SAMPLE;
}

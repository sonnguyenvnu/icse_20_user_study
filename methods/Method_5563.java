private static Cue readNextSection(ParsableByteArray buffer,CueBuilder cueBuilder){
  int limit=buffer.limit();
  int sectionType=buffer.readUnsignedByte();
  int sectionLength=buffer.readUnsignedShort();
  int nextSectionPosition=buffer.getPosition() + sectionLength;
  if (nextSectionPosition > limit) {
    buffer.setPosition(limit);
    return null;
  }
  Cue cue=null;
switch (sectionType) {
case SECTION_TYPE_PALETTE:
    cueBuilder.parsePaletteSection(buffer,sectionLength);
  break;
case SECTION_TYPE_BITMAP_PICTURE:
cueBuilder.parseBitmapSection(buffer,sectionLength);
break;
case SECTION_TYPE_IDENTIFIER:
cueBuilder.parseIdentifierSection(buffer,sectionLength);
break;
case SECTION_TYPE_END:
cue=cueBuilder.build();
cueBuilder.reset();
break;
default :
break;
}
buffer.setPosition(nextSectionPosition);
return cue;
}

@Override public int read(ExtractorInput input,PositionHolder seekPosition) throws IOException, InterruptedException {
  while (true) {
switch (parserState) {
case STATE_READING_ATOM_HEADER:
      if (!readAtomHeader(input)) {
        return RESULT_END_OF_INPUT;
      }
    break;
case STATE_READING_ATOM_PAYLOAD:
  if (readAtomPayload(input,seekPosition)) {
    return RESULT_SEEK;
  }
break;
case STATE_READING_SAMPLE:
return readSample(input,seekPosition);
default :
throw new IllegalStateException();
}
}
}

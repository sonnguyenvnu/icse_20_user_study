private boolean sniffInternal(ExtractorInput input) throws IOException, InterruptedException {
  OggPageHeader header=new OggPageHeader();
  if (!header.populate(input,true) || (header.type & 0x02) != 0x02) {
    return false;
  }
  int length=Math.min(header.bodySize,MAX_VERIFICATION_BYTES);
  ParsableByteArray scratch=new ParsableByteArray(length);
  input.peekFully(scratch.data,0,length);
  if (FlacReader.verifyBitstreamType(resetPosition(scratch))) {
    streamReader=new FlacReader();
  }
 else   if (VorbisReader.verifyBitstreamType(resetPosition(scratch))) {
    streamReader=new VorbisReader();
  }
 else   if (OpusReader.verifyBitstreamType(resetPosition(scratch))) {
    streamReader=new OpusReader();
  }
 else {
    return false;
  }
  return true;
}

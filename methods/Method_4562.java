private ParsableByteArray prepareTagData(ExtractorInput input) throws IOException, InterruptedException {
  if (tagDataSize > tagData.capacity()) {
    tagData.reset(new byte[Math.max(tagData.capacity() * 2,tagDataSize)],0);
  }
 else {
    tagData.setPosition(0);
  }
  tagData.setLimit(tagDataSize);
  input.readFully(tagData.data,0,tagDataSize);
  return tagData;
}

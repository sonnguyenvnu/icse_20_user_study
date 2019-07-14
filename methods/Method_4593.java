/** 
 * Peeks a variable-length unsigned EBML integer from the input.
 */
private long readUint(ExtractorInput input) throws IOException, InterruptedException {
  input.peekFully(scratch.data,0,1);
  int value=scratch.data[0] & 0xFF;
  if (value == 0) {
    return Long.MIN_VALUE;
  }
  int mask=0x80;
  int length=0;
  while ((value & mask) == 0) {
    mask>>=1;
    length++;
  }
  value&=~mask;
  input.peekFully(scratch.data,1,length);
  for (int i=0; i < length; i++) {
    value<<=8;
    value+=scratch.data[i + 1] & 0xFF;
  }
  peekLength+=length + 1;
  return value;
}

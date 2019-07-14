protected int getMaxTexUnits(){
  intBuffer.rewind();
  getIntegerv(MAX_TEXTURE_IMAGE_UNITS,intBuffer);
  return intBuffer.get(0);
}

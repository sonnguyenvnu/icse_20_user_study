private void flushCurrentMdat() throws Exception {
  long oldPosition=fc.position();
  fc.position(mdat.getOffset());
  mdat.getBox(fc);
  fc.position(oldPosition);
  mdat.setDataOffset(0);
  mdat.setContentSize(0);
  fos.flush();
  fos.getFD().sync();
}

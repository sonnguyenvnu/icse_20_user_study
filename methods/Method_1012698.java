@Override public long length(){
  if (getPlayer() != null && getPlayer().type() != Format.IMAGE) {
    return DLNAMediaInfo.TRANS_SIZE;
  }
  return length;
}

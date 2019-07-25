public boolean[] pieces(){
  PieceIndexBitfield bitfield=th.status(TorrentHandle.QUERY_PIECES).pieces();
  boolean[] pieces=new boolean[bitfield.size()];
  for (int i=0; i < bitfield.size(); i++)   pieces[i]=bitfield.getBit(i);
  return pieces;
}

private void buildFromKeyset(Keyset keyset,int begin,int end,int depth,int dicId){
  int offset=arrangeFromKeyset(keyset,begin,end,depth,dicId);
  while (begin < end) {
    if (keyset.getKeyByte(begin,depth) != 0) {
      break;
    }
    ++begin;
  }
  if (begin == end) {
    return;
  }
  int lastBegin=begin;
  byte lastLabel=keyset.getKeyByte(begin,depth);
  while (++begin < end) {
    byte label=keyset.getKeyByte(begin,depth);
    if (label != lastLabel) {
      buildFromKeyset(keyset,lastBegin,begin,depth + 1,offset ^ (lastLabel & 0xFF));
      lastBegin=begin;
      lastLabel=keyset.getKeyByte(begin,depth);
    }
  }
  buildFromKeyset(keyset,lastBegin,end,depth + 1,offset ^ (lastLabel & 0xFF));
}

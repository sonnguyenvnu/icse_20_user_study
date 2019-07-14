private int arrangeFromDawg(DawgBuilder dawg,int dawgId,int dictId){
  _labels.resize(0);
  int dawgChildId=dawg.child(dawgId);
  while (dawgChildId != 0) {
    _labels.add(dawg.label(dawgChildId));
    dawgChildId=dawg.sibling(dawgChildId);
  }
  int offset=findValidOffset(dictId);
  int[] units=_units.getBuffer();
  units[dictId]&=OFFSET_MASK;
  int newId=dictId ^ offset;
  units[dictId]|=(newId < 1 << 21) ? newId << 10 : (newId << 2) | (1 << 9);
  dawgChildId=dawg.child(dawgId);
  for (int i=0; i < _labels.size(); ++i) {
    int dictChildId=offset ^ (_labels.get(i) & 0xFF);
    reserveId(dictChildId);
    units=_units.getBuffer();
    if (dawg.isLeaf(dawgChildId)) {
      units[dictId]|=1 << 8;
      units[dictChildId]=dawg.value(dawgChildId) | (1 << 31);
    }
 else {
      units[dictChildId]=(units[dictChildId] & ~0xFF) | (_labels.get(i) & 0xFF);
    }
    dawgChildId=dawg.sibling(dawgChildId);
  }
  extras(offset).isUsed=true;
  return offset;
}

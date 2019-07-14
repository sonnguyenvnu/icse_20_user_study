private int hashUnit(int id){
  int hashValue=0;
  for (; id != 0; ++id) {
    int unit=_units.get(id);
    byte label=_labels.get(id);
    hashValue^=hash(((label & 0xFF) << 24) ^ unit);
    if ((_units.get(id) & 1) != 1) {
      break;
    }
  }
  return hashValue;
}

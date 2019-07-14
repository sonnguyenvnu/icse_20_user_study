/** 
 * ?????
 */
void append(){
  if ((_size % UNIT_SIZE) == 0) {
    _units.add(0);
  }
  ++_size;
}

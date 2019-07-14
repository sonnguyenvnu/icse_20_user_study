/** 
 * ????????
 * @param id ?
 * @return ???1??0
 */
boolean get(int id){
  return (_units.get(id / UNIT_SIZE) >>> (id % UNIT_SIZE) & 1) == 1;
}

/** 
 * @return the sequence part of an id.
 */
static int getSequenceFromId(long id){
  return (int)id & 0x00FFFF;
}

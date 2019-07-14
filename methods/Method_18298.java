/** 
 * @return the type part of an id. 
 */
static @OutputUnitType int getTypeFromId(long id){
  if (id == MountState.ROOT_HOST_ID) {
    return OutputUnitType.HOST;
  }
  return (int)((id >> TYPE_SHIFT) & 0x3);
}

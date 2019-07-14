/** 
 * ??????????
 * @return
 */
public boolean isOffline(){
  if (status == InstanceStatusEnum.OFFLINE_STATUS.getStatus()) {
    return true;
  }
  return false;
}

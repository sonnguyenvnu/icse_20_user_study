/** 
 * ??????.
 * @return ????
 */
public String getTaskName(){
  return Joiner.on(DELIMITER).join(metaInfo,type,slaveId);
}

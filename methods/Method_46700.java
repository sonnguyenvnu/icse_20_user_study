/** 
 * ??????
 * @param groupId groupId
 * @param unitId  unitId
 * @return aspect log
 */
public static MessageDto getAspectLog(String groupId,String unitId){
  GetAspectLogParams getAspectLogParams=new GetAspectLogParams();
  getAspectLogParams.setGroupId(groupId);
  getAspectLogParams.setUnitId(unitId);
  MessageDto messageDto=new MessageDto();
  messageDto.setGroupId(groupId);
  messageDto.setAction(MessageConstants.ACTION_GET_ASPECT_LOG);
  messageDto.setData(getAspectLogParams);
  return messageDto;
}

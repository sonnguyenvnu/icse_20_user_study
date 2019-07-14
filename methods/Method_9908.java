/** 
 * Submits the specified character to recognize.
 * @param userId       the specified user id
 * @param characterImg the specified character image encoded by Base64
 * @param character    the specified character
 * @return recognition result
 */
public synchronized JSONObject submitCharacter(final String userId,final String characterImg,final String character){
  String recongnizeFailedMsg=langPropsService.get("activityCharacterRecognizeFailedLabel");
  final JSONObject ret=new JSONObject();
  ret.put(Keys.STATUS_CODE,false);
  ret.put(Keys.MSG,recongnizeFailedMsg);
  if (StringUtils.isBlank(characterImg) || StringUtils.isBlank(character)) {
    ret.put(Keys.STATUS_CODE,false);
    ret.put(Keys.MSG,recongnizeFailedMsg);
    return ret;
  }
  final byte[] data=Base64.decode(characterImg);
  OutputStream stream=null;
  final String tmpDir=System.getProperty("java.io.tmpdir");
  final String imagePath=tmpDir + "/" + userId + "-character.png";
  try {
    stream=new FileOutputStream(imagePath);
    stream.write(data);
    stream.flush();
    stream.close();
  }
 catch (  final IOException e) {
    LOGGER.log(Level.ERROR,"Submits character failed",e);
    return ret;
  }
 finally {
    if (null != stream) {
      try {
        stream.close();
      }
 catch (      final IOException ex) {
        LOGGER.log(Level.ERROR,"Closes stream failed",ex);
      }
    }
  }
  final String recognizedCharacter=Tesseracts.recognizeCharacter(imagePath);
  LOGGER.info("Character [" + character + "], recognized [" + recognizedCharacter + "], image path [" + imagePath + "]");
  if (StringUtils.equals(character,recognizedCharacter)) {
    final Query query=new Query();
    query.setFilter(CompositeFilterOperator.and(new PropertyFilter(org.b3log.symphony.model.Character.CHARACTER_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(org.b3log.symphony.model.Character.CHARACTER_CONTENT,FilterOperator.EQUAL,character)));
    try {
      if (characterRepository.count(query) > 0) {
        return ret;
      }
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Count characters failed [userId=" + userId + ", character=" + character + "]",e);
      return ret;
    }
    final JSONObject record=new JSONObject();
    record.put(org.b3log.symphony.model.Character.CHARACTER_CONTENT,character);
    record.put(org.b3log.symphony.model.Character.CHARACTER_IMG,characterImg);
    record.put(org.b3log.symphony.model.Character.CHARACTER_USER_ID,userId);
    String characterId;
    final Transaction transaction=characterRepository.beginTransaction();
    try {
      characterId=characterRepository.add(record);
      transaction.commit();
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Submits character failed",e);
      if (null != transaction) {
        transaction.rollback();
      }
      return ret;
    }
    pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_CHARACTER,Pointtransfer.TRANSFER_SUM_C_ACTIVITY_CHARACTER,characterId,System.currentTimeMillis(),"");
    ret.put(Keys.STATUS_CODE,true);
    ret.put(Keys.MSG,langPropsService.get("activityCharacterRecognizeSuccLabel"));
  }
 else {
    recongnizeFailedMsg=recongnizeFailedMsg.replace("{?}",recognizedCharacter);
    ret.put(Keys.STATUS_CODE,false);
    ret.put(Keys.MSG,recongnizeFailedMsg);
  }
  return ret;
}

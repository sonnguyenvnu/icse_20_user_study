/** 
 * Sets a user's emotions.
 * @param userId      the specified user id
 * @param emotionList the specified emotions
 * @throws ServiceException service exception
 */
public void setEmotionList(final String userId,final String emotionList) throws ServiceException {
  final Transaction transaction=emotionRepository.beginTransaction();
  try {
    emotionRepository.removeByUserId(userId);
    final Set<String> emotionSet=new HashSet<>();
    final String[] emotionArray=emotionList.split(",");
    for (int i=0, sort=0; i < emotionArray.length; i++) {
      final String content=emotionArray[i];
      if (StringUtils.isBlank(content) || emotionSet.contains(content) || !Emotions.isEmoji(content)) {
        continue;
      }
      final JSONObject userEmotion=new JSONObject();
      userEmotion.put(Emotion.EMOTION_USER_ID,userId);
      userEmotion.put(Emotion.EMOTION_CONTENT,content);
      userEmotion.put(Emotion.EMOTION_SORT,sort++);
      userEmotion.put(Emotion.EMOTION_TYPE,Emotion.EMOTION_TYPE_C_EMOJI);
      emotionRepository.add(userEmotion);
      emotionSet.add(content);
    }
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Set user emotion list failed [id=" + userId + "]",e);
    if (null != transaction && transaction.isActive()) {
      transaction.rollback();
    }
    throw new ServiceException(e);
  }
}

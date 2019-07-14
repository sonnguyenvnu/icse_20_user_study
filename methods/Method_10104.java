/** 
 * Removes unused tags.
 */
@Transactional public synchronized void removeUnusedTags(){
  LOGGER.info("Starting remove unused tags....");
  int removedCnt=0;
  try {
    final JSONArray tags=tagRepository.get(new Query().setFilter(new PropertyFilter(Tag.TAG_REFERENCE_CNT,FilterOperator.EQUAL,0))).optJSONArray(Keys.RESULTS);
    for (int i=0; i < tags.length(); i++) {
      JSONObject tag=tags.optJSONObject(i);
      final String tagId=tag.optString(Keys.OBJECT_ID);
      if (0 < tag.optInt(Tag.TAG_REFERENCE_CNT) || 0 < domainTagRepository.getByTagId(tagId,1,Integer.MAX_VALUE).optJSONArray(Keys.RESULTS).length()) {
        continue;
      }
      final JSONArray userFollowTags=followRepository.getByFollowingId(tagId,Follow.FOLLOWING_TYPE_C_TAG,1,Integer.MAX_VALUE).optJSONArray(Keys.RESULTS);
      for (int j=0; j < userFollowTags.length(); j++) {
        final JSONObject userFollowTag=userFollowTags.optJSONObject(j);
        if (Follow.FOLLOWING_TYPE_C_TAG == userFollowTag.optInt(Follow.FOLLOWING_TYPE)) {
          final String followerId=userFollowTag.optString(Follow.FOLLOWER_ID);
          followMgmtService.unfollowTag(followerId,tagId);
        }
      }
      final JSONArray userTagRels=userTagRepository.getByTagId(tagId,1,Integer.MAX_VALUE).optJSONArray(Keys.RESULTS);
      if (1 == userTagRels.length() && Tag.TAG_TYPE_C_CREATOR == userTagRels.optJSONObject(0).optInt(Common.TYPE)) {
        final String tagTitle=tag.optString(Tag.TAG_TITLE);
        if (StringUtils.isBlank(tag.optString(Tag.TAG_ICON_PATH)) && StringUtils.isBlank(tag.optString(Tag.TAG_DESCRIPTION))) {
          tagRepository.remove(tagId);
          removedCnt++;
          LOGGER.info("Removed a unused tag [title=" + tagTitle + "]");
        }
 else {
          LOGGER.info("Found a unused tag [title=" + tagTitle + "], but it has description or icon so do not remove it");
        }
      }
    }
    final JSONObject tagCntOption=optionRepository.get(Option.ID_C_STATISTIC_TAG_COUNT);
    final int tagCnt=tagCntOption.optInt(Option.OPTION_VALUE);
    tagCntOption.put(Option.OPTION_VALUE,tagCnt - removedCnt);
    optionRepository.update(Option.ID_C_STATISTIC_TAG_COUNT,tagCntOption);
    LOGGER.info("Removed [" + removedCnt + "] unused tags");
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Removes unused tags failed",e);
  }
}

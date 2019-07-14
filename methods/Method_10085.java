/** 
 * Adds or updates a referral.
 * @param referral the specified referral
 */
@Transactional public void updateReferral(final JSONObject referral){
  final String dataId=referral.optString(Referral.REFERRAL_DATA_ID);
  final String ip=referral.optString(Referral.REFERRAL_IP);
  try {
    JSONObject record=referralRepository.getByDataIdAndIP(dataId,ip);
    if (null == record) {
      record=new JSONObject();
      record.put(Referral.REFERRAL_AUTHOR_HAS_POINT,false);
      record.put(Referral.REFERRAL_CLICK,1);
      record.put(Referral.REFERRAL_DATA_ID,dataId);
      record.put(Referral.REFERRAL_IP,ip);
      record.put(Referral.REFERRAL_TYPE,referral.optInt(Referral.REFERRAL_TYPE));
      record.put(Referral.REFERRAL_USER,referral.optString(Referral.REFERRAL_USER));
      record.put(Referral.REFERRAL_USER_HAS_POINT,false);
      referralRepository.add(record);
    }
 else {
      final String currentReferralUser=referral.optString(Referral.REFERRAL_USER);
      final String firstReferralUser=record.optString(Referral.REFERRAL_USER);
      if (!currentReferralUser.equals(firstReferralUser)) {
        return;
      }
      record.put(Referral.REFERRAL_CLICK,record.optInt(Referral.REFERRAL_CLICK) + 1);
      referralRepository.update(record.optString(Keys.OBJECT_ID),record);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Updates a referral failed",e);
  }
}

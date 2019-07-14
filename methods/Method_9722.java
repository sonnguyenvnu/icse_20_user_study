/** 
 * Calculates point of the specified liveness.
 * @param liveness the specified liveness
 * @return point
 */
public static int calcPoint(final JSONObject liveness){
  final float activityPer=Symphonys.ACTIVITY_YESTERDAY_REWARD_ACTIVITY_PER;
  final float articlePer=Symphonys.ACTIVITY_YESTERDAY_REWARD_ARTICLE_PER;
  final float commentPer=Symphonys.ACTIVITY_YESTERDAY_REWARD_COMMENT_PER;
  final float pvPer=Symphonys.ACTIVITY_YESTERDAY_REWARD_PV_PER;
  final float rewardPer=Symphonys.ACTIVITY_YESTERDAY_REWARD_REWARD_PER;
  final float thankPer=Symphonys.ACTIVITY_YESTERDAY_REWARD_THANK_PER;
  final float votePer=Symphonys.ACTIVITY_YESTERDAY_REWARD_VOTE_PER;
  final float acceptAnswerPer=Symphonys.ACTIVITY_YESTERDAY_REWARD_ACCEPT_ANSWER_PER;
  final int activity=liveness.optInt(Liveness.LIVENESS_ACTIVITY);
  final int article=liveness.optInt(Liveness.LIVENESS_ARTICLE);
  final int comment=liveness.optInt(Liveness.LIVENESS_COMMENT);
  int pv=liveness.optInt(Liveness.LIVENESS_PV);
  if (pv > 50) {
    pv=50;
  }
  final int reward=liveness.optInt(Liveness.LIVENESS_REWARD);
  final int thank=liveness.optInt(Liveness.LIVENESS_THANK);
  int vote=liveness.optInt(Liveness.LIVENESS_VOTE);
  if (vote > 10) {
    vote=10;
  }
  final int acceptAnswer=liveness.optInt(Liveness.LIVENESS_ACCEPT_ANSWER);
  final int activityPoint=(int)(activity * activityPer);
  final int articlePoint=(int)(article * articlePer);
  final int commentPoint=(int)(comment * commentPer);
  final int pvPoint=(int)(pv * pvPer);
  final int rewardPoint=(int)(reward * rewardPer);
  final int thankPoint=(int)(thank * thankPer);
  final int votePoint=(int)(vote * votePer);
  final int acceptAnswerPoint=(int)(acceptAnswer * acceptAnswerPer);
  int ret=activityPoint + articlePoint + commentPoint + pvPoint + rewardPoint + thankPoint + votePoint + acceptAnswerPoint;
  final int max=Symphonys.ACTIVITY_YESTERDAY_REWARD_MAX;
  if (ret > max) {
    ret=max;
  }
  return ret;
}

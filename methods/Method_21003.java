public @NonNull String rewardSelectedUrl(final @NonNull Reward reward){
  return Uri.parse(newPledgeUrl()).buildUpon().scheme("https").appendQueryParameter("backing[backer_reward_id]",String.valueOf(reward.id())).appendQueryParameter("clicked_reward","true").build().toString();
}

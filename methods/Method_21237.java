/** 
 * Populate adapter data when we know we're working with a Project object.
 */
public void takeProject(final @NonNull Project project,final @NonNull String configCountry,final @NonNull Boolean nativeCheckout){
  sections().clear();
  sections().add(Collections.singletonList(Pair.create(project,configCountry)));
  final List<Reward> rewards=project.rewards();
  if (rewards != null && !nativeCheckout) {
    addSection(Observable.from(rewards).filter(RewardUtils::isReward).map(reward -> Pair.create(project,reward)).toList().toBlocking().single());
  }
  notifyDataSetChanged();
}

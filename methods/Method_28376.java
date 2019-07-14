@Override public void updatePinned(int forks,int stars,int watching){
  this.repo.setStargazersCount(stars);
  this.repo.setForksCount(forks);
  this.repo.setSubsCount(watching);
  updatePinned(repo);
}

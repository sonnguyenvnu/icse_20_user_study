@Activate protected void activate(){
  refreshSitemapModels();
  modelRepo.addModelRepositoryChangeListener(this);
}

protected void deactivate(){
  active=false;
  modelRepository.removeModelRepositoryChangeListener(this);
}

protected void hook(){
  if (!EntityHolder.listAll().contains(this.clz)) {
    EntityHolder.listAll().add(this.clz);
  }
  if (!HealthChecker.getRepositoryList().contains(this)) {
    HealthChecker.getRepositoryList().add(this);
  }
}

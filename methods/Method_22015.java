@Override public void start(){
  addDataListener(new LeaderElectionJobListener());
  addDataListener(new LeaderAbdicationJobListener());
}

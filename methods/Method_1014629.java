private void reset(CpuLayout cpuLayout){
  this.cpuLayout=cpuLayout;
  this.logicalCoreLocks=new AffinityLock[cpuLayout.cpus()];
  this.physicalCoreLocks.clear();
}

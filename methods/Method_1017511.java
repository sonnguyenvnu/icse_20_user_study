@Override public void add(Map<String,Object> map){
  StateMachineTrace trace=new StateMachineTrace(new Date(),map);
synchronized (this.traces) {
    while (this.traces.size() >= this.capacity) {
      this.traces.remove(this.reverse ? this.capacity - 1 : 0);
    }
    if (this.reverse) {
      this.traces.add(0,trace);
    }
 else {
      this.traces.add(trace);
    }
  }
}

public void push(IPerformanceTracer other){
  assert other != null;
  if (!(other instanceof PerformanceTracer)) {
    addText(other.report());
    return;
  }
  assert other != this;
  PerformanceTracer ptOther=(PerformanceTracer)other;
  if (ptOther.top != 0) {
    throw new IllegalArgumentException("Another tracer is in incomplete state (still in use?)");
  }
  getTask(top).tasks.addAll(ptOther.myStack[0].task.tasks);
}

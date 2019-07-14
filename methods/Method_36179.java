public int comparePriorityWith(StubMapping otherMapping){
  int thisPriority=priority != null ? priority : DEFAULT_PRIORITY;
  int otherPriority=otherMapping.priority != null ? otherMapping.priority : DEFAULT_PRIORITY;
  return thisPriority - otherPriority;
}

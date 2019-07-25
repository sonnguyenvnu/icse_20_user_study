@SuppressWarnings({"rawtypes"}) static void report(Progressable progressable,Stats stats){
  progressable=(Progressable)CompatHandler.unwrap(progressable);
  if (progressable == null || progressable == Reporter.NULL) {
    return;
  }
  if (progressable instanceof Reporter) {
    Reporter reporter=(Reporter)progressable;
    for (    Counter count : Counter.ALL) {
      oldApiCounter(reporter,count,count.get(stats));
    }
  }
  if (progressable instanceof org.apache.hadoop.mapreduce.TaskInputOutputContext) {
    TaskInputOutputContext compatTioc=CompatHandler.taskInputOutputContext((org.apache.hadoop.mapreduce.TaskInputOutputContext)progressable);
    for (    Counter count : Counter.ALL) {
      newApiCounter(compatTioc,count,count.get(stats));
    }
  }
}

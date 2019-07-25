@Override protected Long compute(){
  if (end - start <= threshold) {
    long count=0;
    for (int i=0; i <= end - start; i++) {
      count=count + start + i;
    }
    return count;
  }
 else {
    long slip=(end - start) / 3;
    ForkJoinCountTask oneTask=new ForkJoinCountTask(start,start + slip);
    ForkJoinCountTask twoTask=new ForkJoinCountTask(start + slip + 1,start + slip * 2);
    ForkJoinCountTask threeTask=new ForkJoinCountTask(start + slip * 2 + 1,end);
    invokeAll(oneTask,twoTask,threeTask);
    return oneTask.join() + twoTask.join() + threeTask.join();
  }
}

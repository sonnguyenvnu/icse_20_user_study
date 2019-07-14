protected int getDownloadedLengthFromOffset(final int offset,final int length){
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  final int[] result=new int[1];
  Utilities.stageQueue.postRunnable(() -> {
    result[0]=getDownloadedLengthFromOffsetInternal(notLoadedBytesRanges,offset,length);
    countDownLatch.countDown();
  }
);
  try {
    countDownLatch.await();
  }
 catch (  Exception ignore) {
  }
  return result[0];
}

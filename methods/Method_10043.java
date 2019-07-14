private void singlePing(final JSONObject link){
  try {
    final CountDownLatch countDownLatch=new CountDownLatch(1);
    pingLink(link,countDownLatch);
    countDownLatch.await(10,TimeUnit.SECONDS);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Pings link [addr=" + link.optString(Link.LINK_ADDR) + "] failed",e);
  }
}

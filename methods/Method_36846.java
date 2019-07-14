@Keep public void parseMeta(Event event){
  try {
    if (mTotalPageCount != Integer.MAX_VALUE) {
      storeCache();
    }
    mIndex=Integer.parseInt(event.args.get(KEY_INDEX));
    mTotalPageCount=Integer.parseInt(event.args.get(KEY_PAGE_COUNT));
  }
 catch (  Exception e) {
  }
}

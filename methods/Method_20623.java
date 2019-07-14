public void trackActivityView(final int pageCount){
  if (pageCount == 0) {
    this.client.track(KoalaEvent.ACTIVITY_VIEW);
  }
 else {
    this.client.track(KoalaEvent.ACTIVITY_LOAD_MORE,new HashMap<String,Object>(){
{
        put("page_count",pageCount);
      }
    }
);
  }
}

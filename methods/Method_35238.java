@Override public void destroyItem(ViewGroup container,int position,Object object){
  Router router=(Router)object;
  Bundle savedState=new Bundle();
  router.saveInstanceState(savedState);
  savedPages.put(position,savedState);
  savedPageHistory.remove((Integer)position);
  savedPageHistory.add(position);
  ensurePagesSaved();
  host.removeChildRouter(router);
  visibleRouters.remove(position);
}

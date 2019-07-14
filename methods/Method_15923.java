static void doPaging(int pageIndex,int pageSize){
  ThreadLocalUtils.put(threadLocalKey,new Pager(){
    @Override public int pageIndex(){
      return pageIndex;
    }
    @Override public int pageSize(){
      return pageSize;
    }
  }
);
}

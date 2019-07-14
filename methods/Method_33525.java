/** 
 * ????????
 */
public void getAllData(){
  Runnable runnable=new Runnable(){
    @Override public void run(){
      try {
        List<User> waits=mUserDao.findUsers();
        mAppExecutors.mainThread().execute(new Runnable(){
          @Override public void run(){
          }
        }
);
      }
 catch (      Exception e) {
        DebugUtil.error(e.getMessage());
      }
    }
  }
;
  mAppExecutors.diskIO().execute(runnable);
}

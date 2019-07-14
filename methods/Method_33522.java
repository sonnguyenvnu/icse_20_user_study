/** 
 * ?????bean(?????????)? ?????????????????? ????????????????
 */
public void getSingleBean(UserDataCallback callback){
  Runnable runnable=new Runnable(){
    @Override public void run(){
      try {
        User user=mUserDao.findSingleBean();
        mAppExecutors.mainThread().execute(new Runnable(){
          @Override public void run(){
            if (user == null) {
              callback.onDataNotAvailable();
            }
 else {
              callback.getData(user);
            }
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

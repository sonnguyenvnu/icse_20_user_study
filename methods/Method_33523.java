/** 
 * ???????: ?????
 */
public void addData(@NonNull User user){
  Runnable runnable=new Runnable(){
    @Override public void run(){
      try {
        int success=mUserDao.deleteAll();
        DebugUtil.error("----success:" + success);
        mUserDao.addUser(user);
      }
 catch (      Exception e) {
        DebugUtil.error(e.getMessage());
      }
    }
  }
;
  mAppExecutors.diskIO().execute(runnable);
}

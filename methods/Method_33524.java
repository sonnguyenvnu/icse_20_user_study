/** 
 * ????
 */
public void updateData(@NonNull User user){
  Runnable saveRunnable=new Runnable(){
    @Override public void run(){
      try {
        mUserDao.addUser(user);
      }
 catch (      Exception e) {
        DebugUtil.error(e.getMessage());
      }
    }
  }
;
  mAppExecutors.diskIO().execute(saveRunnable);
}

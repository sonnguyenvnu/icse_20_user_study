/** 
 * save the bound service
 * @param iServiceConnection IServiceConnection binder when bindService
 * @return
 */
public void remberIServiceConnection(IBinder iServiceConnection,Intent intent){
synchronized (this.mBoundServices) {
    mBoundServices.put(iServiceConnection,intent);
  }
}

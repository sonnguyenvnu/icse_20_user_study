/** 
 * ??????
 * @param mContext  ????Context
 * @param success   ??????
 * @param pakName   ????
 * @param errorCode ???
 */
public static void deliver(final Context mContext,final boolean success,final String pakName,final int errorCode){
  if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
    new AsyncTask<Void,Void,Void>(){
      @Override protected Void doInBackground(      Void... params){
        deliverPlugInner(mContext,success,pakName,errorCode);
        return null;
      }
    }
.execute();
  }
 else {
    deliverPlugInner(mContext,success,pakName,errorCode);
  }
}

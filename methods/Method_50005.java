/** 
 * Get(or create if not exist) an instance of PduPersister
 */
public static PduPersister getPduPersister(Context context){
  if ((sPersister == null)) {
    sPersister=new PduPersister(context);
  }
 else   if (!context.equals(sPersister.mContext)) {
    sPersister.release();
    sPersister=new PduPersister(context);
  }
  return sPersister;
}

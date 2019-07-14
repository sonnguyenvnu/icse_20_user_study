/** 
 * Cast an IBinder object into an IBookManager interface, generating a proxy if needed.
 */
public static IBookManager asInterface(IBinder obj){
  if ((obj == null)) {
    return null;
  }
  android.os.IInterface iin=obj.queryLocalInterface(DESCRIPTOR);
  if (((iin != null) && (iin instanceof IBookManager))) {
    return ((IBookManager)iin);
  }
  return new BookManagerImpl.Proxy(obj);
}

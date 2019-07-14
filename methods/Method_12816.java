@Override public boolean onTransact(int code,Parcel data,Parcel reply,int flags) throws RemoteException {
switch (code) {
case INTERFACE_TRANSACTION:
{
      reply.writeString(DESCRIPTOR);
      return true;
    }
case TRANSACTION_getBookList:
{
    data.enforceInterface(DESCRIPTOR);
    List<Book> result=this.getBookList();
    reply.writeNoException();
    reply.writeTypedList(result);
    return true;
  }
case TRANSACTION_addBook:
{
  data.enforceInterface(DESCRIPTOR);
  Book arg0;
  if ((0 != data.readInt())) {
    arg0=Book.CREATOR.createFromParcel(data);
  }
 else {
    arg0=null;
  }
  this.addBook(arg0);
  reply.writeNoException();
  return true;
}
}
return super.onTransact(code,data,reply,flags);
}

public TLObject TLdeserialize(NativeByteBuffer stream,int constructor,boolean exception){
  Class objClass=classStore.get(constructor);
  if (objClass != null) {
    TLObject response;
    try {
      response=(TLObject)objClass.newInstance();
    }
 catch (    Throwable e) {
      FileLog.e(e);
      return null;
    }
    response.readParams(stream,exception);
    return response;
  }
  return null;
}

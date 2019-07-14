@Override public Type make(API api,Container.Entry entry,String fragment){
  Listener listener=getListener(entry);
  if (listener == null) {
    return null;
  }
 else {
    if ((fragment != null) && (fragment.length() > 0)) {
      int index=fragment.indexOf('-');
      if (index != -1) {
        fragment=fragment.substring(0,index);
      }
      return listener.getType(fragment);
    }
 else {
      return listener.getMainType();
    }
  }
}

private void onNewBookArrived(Book book) throws RemoteException {
  mBookList.add(book);
  final int N=mListenerList.beginBroadcast();
  for (int i=0; i < N; i++) {
    IOnNewBookArrivedListener l=mListenerList.getBroadcastItem(i);
    if (l != null) {
      try {
        l.onNewBookArrived(book);
      }
 catch (      RemoteException e) {
        e.printStackTrace();
      }
    }
  }
  mListenerList.finishBroadcast();
}

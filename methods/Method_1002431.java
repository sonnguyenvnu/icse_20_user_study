public void iterate(Callback callback){
  DataIterator it=dataIterator();
  DataElement element;
  while ((element=it.next()) != null) {
    callback.callback(element);
  }
}

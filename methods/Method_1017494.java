public void refresh(Table... tables){
synchronized (this) {
    Iterator<WeakReference<LiveResults<?>>> iterator=liveDatas.iterator();
    while (iterator.hasNext()) {
      WeakReference<LiveResults<?>> weakReference=iterator.next();
      LiveResults<?> liveData=weakReference.get();
      if (liveData == null) {
        iterator.remove();
      }
 else {
        boolean isInTables=false;
        Table table=liveData.table;
        for (        Table currentTable : tables) {
          if (currentTable == table) {
            isInTables=true;
            break;
          }
        }
        if (isInTables) {
          liveData.refresh();
        }
      }
    }
  }
}

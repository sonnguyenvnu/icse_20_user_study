public void changed(final DataSource source){
synchronized (LOCK) {
    ListSequence.fromList(myListeners).visitAll(new IVisitor<DataSourceListener>(){
      public void visit(      DataSourceListener it){
        it.changed(source);
      }
    }
);
  }
}

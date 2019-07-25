public void changed(final DataSource source,final PsiJavaStubEvent event){
synchronized (LOCK) {
    ListSequence.fromList(myListeners).visitAll(new IVisitor<DataSourceListener>(){
      public void visit(      DataSourceListener it){
        if (it instanceof PsiJavaStubListener) {
          ((PsiJavaStubListener)it).changed(source,event);
        }
 else {
          it.changed(source);
        }
      }
    }
);
  }
}

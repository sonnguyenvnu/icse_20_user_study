private boolean open(final _FunctionTypes._void_P1_E1<? super IProjectHandler,? extends RemoteException> todo,final MPSProject p){
  final Wrappers._boolean result=new Wrappers._boolean(false);
  ApplicationManager.getApplication().executeOnPooledThread(new Runnable(){
    public void run(){
      IProjectHandler handler=MPSPlugin.getInstance().getProjectHandler(check_tz3sru_a0a0a0a0a1a5(p.getProjectFile()));
      if (handler != null) {
        try {
          todo.invoke(handler);
          result.value=true;
        }
 catch (        RemoteException e) {
          e.printStackTrace();
        }
      }
    }
  }
);
  return result.value;
}

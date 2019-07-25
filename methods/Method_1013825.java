@Override public void handle(Channel channel,String[] content){
  sequentiallyExecute(new AbstractExceptionLogTask(){
    @Override protected void doRun(){
      getHandler(content[0]).handle(channel,content);
    }
  }
);
}

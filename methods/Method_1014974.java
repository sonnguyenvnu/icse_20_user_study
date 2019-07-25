@Override public void config(){
  addInterceptor(new WorkInterceptor());
  add("/work",WorkController.class);
  add("/task",TaskController.class);
}

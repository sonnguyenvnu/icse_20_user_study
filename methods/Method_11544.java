@Override public void process(ResultItems resultItems,Task task){
  System.out.println("get page: " + resultItems.getRequest().getUrl());
  for (  Map.Entry<String,Object> entry : resultItems.getAll().entrySet()) {
    System.out.println(entry.getKey() + ":\t" + entry.getValue());
  }
}

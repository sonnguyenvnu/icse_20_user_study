public void start(EventBus eventBus){
  pieChart.setWidth("90%");
  pieChart.setHeight("90%");
  pieChart.getElement().getStyle().setMarginLeft(5.0,Unit.PCT);
  pieChart.getElement().getStyle().setMarginTop(5.0,Unit.PCT);
  eventBus.addHandler(TaskListUpdateEvent.TYPE,new TaskListUpdateEvent.Handler(){
    public void onTaskListUpdated(    TaskListUpdateEvent event){
      updatePieChart(event.getTasks());
    }
  }
);
}

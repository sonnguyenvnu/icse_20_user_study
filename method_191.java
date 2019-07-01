public MRTaskExecutionResponse.TaskGroupResponse _XXXXX_(MRTaskExecutionResponse.TaskGroupResponse result,boolean keepShort,List<TaskExecutionAPIEntity> tasks,long value){
  for (  TaskExecutionAPIEntity entity : tasks) {
    String taskType=entity.getTags().get(MRJobTagName.TASK_TYPE.toString());
    MRTaskExecutionResponse.TaskGroup taskGroup=result.tasksGroupByType.get(taskType.toUpperCase());
    if (entity.getDuration() <= value && keepShort) {
      taskGroup.shortTasks.add(entity);
    }
    if (entity.getDuration() > value) {
      taskGroup.longTasks.add(entity);
    }
  }
  return result;
}
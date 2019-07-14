@Override public long getContextCounter(TaskInputOutputContext context,String group,String name){
  return context.getCounter(group,name).getValue();
}

@Nullable protected String createContent(InstanceEvent event,Instance instance){
  Map<String,Object> root=new HashMap<>();
  root.put("event",event);
  root.put("instance",instance);
  root.put("lastStatus",getLastStatus(event.getInstance()));
  StandardEvaluationContext context=new StandardEvaluationContext(root);
  context.addPropertyAccessor(new MapAccessor());
  return message.getValue(context,String.class);
}

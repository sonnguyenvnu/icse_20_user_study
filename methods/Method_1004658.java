@Override protected boolean matches(ResolvedProjectDescription projectDescription,ConditionContext context,AnnotatedTypeMetadata metadata){
  String id=(String)metadata.getAnnotationAttributes(ConditionalOnRequestedDependency.class.getName()).get("value");
  return projectDescription.getRequestedDependencies().containsKey(id);
}

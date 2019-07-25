@Override public void customize(GradleBuild build){
  boolean annotationProcessorUsed=build.dependencies().items().anyMatch((dependency) -> dependency.getScope() == DependencyScope.ANNOTATION_PROCESSOR);
  if (annotationProcessorUsed) {
    build.customizeConfiguration("compileOnly",(configuration) -> configuration.extendsFrom("annotationProcessor"));
  }
}

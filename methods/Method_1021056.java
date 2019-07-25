@SuppressWarnings("NullableProblems") @Override public void apply(Project project){
  AppExtension appExtension=(AppExtension)project.getProperties().get("android");
  appExtension.registerTransform(new LinelogHunterTransform(project),Collections.EMPTY_LIST);
}

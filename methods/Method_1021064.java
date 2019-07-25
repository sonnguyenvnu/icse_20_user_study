@SuppressWarnings("NullableProblems") @Override public void apply(Project project){
  AppExtension appExtension=(AppExtension)project.getProperties().get("android");
  appExtension.registerTransform(new TimingHunterTransform(project),Collections.EMPTY_LIST);
}

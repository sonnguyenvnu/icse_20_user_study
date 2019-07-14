@SuppressWarnings("NullableProblems") @Override public void execute(Project project){
  Objects.requireNonNull(project.getExtensions().findByType(DependencyManagementExtension.class)).imports((importsHandler) -> importsHandler.mavenBom(SofaBootPlugin.BOM_COORDINATES));
}

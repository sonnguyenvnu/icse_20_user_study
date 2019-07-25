@Override public void customize(Build build){
  VersionReference version=determineDependencyVersion(build);
  build.dependencies().add("kotlin-stdlib",Dependency.withCoordinates("org.jetbrains.kotlin","kotlin-stdlib-jdk8").version(version).scope(DependencyScope.COMPILE));
  build.dependencies().add("kotlin-reflect",Dependency.withCoordinates("org.jetbrains.kotlin","kotlin-reflect").version(version).scope(DependencyScope.COMPILE));
}

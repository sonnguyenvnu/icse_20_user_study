@Override public void customize(MavenBuild build){
  MavenPlugin groovyMavenPlugin=build.plugin("org.codehaus.gmavenplus","gmavenplus-plugin","1.6.3");
  groovyMavenPlugin.execution(null,(execution) -> execution.goal("addSources").goal("addTestSources").goal("generateStubs").goal("compile").goal("generateTestStubs").goal("compileTests").goal("removeStubs").goal("removeTestStubs"));
}

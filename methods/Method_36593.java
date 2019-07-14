private void verifyGradleVersion(){
  if (GradleVersion.current().compareTo(GradleVersion.version("4.4")) < 0) {
    throw new GradleException("SOFA Boot plugin requires Gradle 4.4 or later." + " The current version is " + GradleVersion.current());
  }
}

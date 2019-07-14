private void setupClassLoader(){
  try {
    if (auxClasspath != null) {
      project.log("Using auxclasspath: " + auxClasspath,Project.MSG_VERBOSE);
      configuration.prependClasspath(auxClasspath.toString());
    }
  }
 catch (  IOException ioe) {
    throw new BuildException(ioe.getMessage(),ioe);
  }
}

/** 
 * Unpacks a private copy of the JaCoCo agent and populates <code>property</code> with the JVM arguments required to use it. The value set into the property is only valid for the lifetime of the current JVM. The agent jar will be removed on termination of the JVM.
 */
@Override public void execute() throws BuildException {
  if (property == null || property.length() == 0) {
    throw new BuildException("Property is mandatory",getLocation());
  }
  final String jvmArg=isEnabled() ? getLaunchingArgument() : "";
  getProject().setNewProperty(property,jvmArg);
}

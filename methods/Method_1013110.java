/** 
 * remove TLA nature
 */
public void deconfigure() throws CoreException {
  IProjectDescription description=getProject().getDescription();
  ICommand[] commands=description.getBuildSpec();
  for (int i=0; i < commands.length; ++i) {
    String builderName=commands[i].getBuilderName();
    if (builderName.equals(TLAParsingBuilder.BUILDER_ID)) {
      ICommand[] newCommands=new ICommand[commands.length - 1];
      System.arraycopy(commands,0,newCommands,0,i);
      System.arraycopy(commands,i + 1,newCommands,i,commands.length - i - 1);
      description.setBuildSpec(newCommands);
    }
  }
  Activator.getDefault().logDebug("Nature removed");
}

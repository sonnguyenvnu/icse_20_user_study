@NotNull public Builder create(){
  return new Builder(){
    @Override public void build(    @NotNull CompiledServiceBuilderArguments args){
      ServiceContainerRemoteFileStorage extension=getExtension(args.getProject());
      if (extension == null) {
        return;
      }
      this.remoteBuildTime=extension.getState().getBuildTime();
      args.addStreams(extension.getState().getInputStreams());
    }
    @Override public boolean isModified(    @NotNull Project project){
      ServiceContainerRemoteFileStorage extension=getExtension(project);
      long remoteBuildTime=-1;
      if (extension != null) {
        remoteBuildTime=extension.getState().getBuildTime();
      }
      return remoteBuildTime != this.remoteBuildTime;
    }
  }
;
}

@Override public void init(DockerClientConfig dockerClientConfig){
  checkNotNull(dockerClientConfig,"config was not specified");
  this.dockerClientConfig=dockerClientConfig;
}

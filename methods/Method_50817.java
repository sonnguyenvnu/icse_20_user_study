public Path createAuxClasspath(){
  if (auxClasspath == null) {
    auxClasspath=new Path(getProject());
  }
  return auxClasspath.createPath();
}

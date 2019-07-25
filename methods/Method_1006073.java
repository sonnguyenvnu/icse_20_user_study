public boolean exists(){
  return path.isPresent() && Files.exists(path.get());
}

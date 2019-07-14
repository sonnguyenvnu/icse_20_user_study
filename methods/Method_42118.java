String getCameraInfo(){
  if (make != null && model != null) {
    if (model.contains(make))     return model;
    return String.format("%s %s",make,model);
  }
  return null;
}

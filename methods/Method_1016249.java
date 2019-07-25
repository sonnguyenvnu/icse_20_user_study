@Override public Shape produce(){
  if (shapeProvider != null) {
    return shapeProvider.provideShape();
  }
 else {
    return null;
  }
}

static protected void copyImage(PShape src,PShape dest){
  if (src.image != null) {
    dest.texture(src.image);
  }
}

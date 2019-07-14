public boolean isEnabled(int feature){
  return (this.features & feature) != 0;
}

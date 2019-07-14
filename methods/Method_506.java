public final boolean isEnabled(int features,int feature){
  return (this.features & feature) != 0 || (features & feature) != 0;
}

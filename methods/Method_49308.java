public void setPropertyMixing(PropertyMixing mixin){
  Preconditions.checkNotNull(mixin);
  Preconditions.checkArgument(this.mixin == NO_MIXIN,"A property mixing has already been set");
  this.mixin=mixin;
}

@Override @SuppressWarnings("unchecked") public MountTo apply(final MocoConfig config){
  if (config.isFor(MocoConfig.URI_ID)) {
    return new MountTo((String)config.apply(this.target));
  }
  return this;
}

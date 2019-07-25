@Override public Settings copy(){
  return new FilteredSettings(parent.copy(),excludePrefix);
}

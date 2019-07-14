@Override public JoyProps addPropsProfiles(final String... profiles){
  requireNotStarted(props);
  Collections.addAll(propsProfiles,profiles);
  return this;
}

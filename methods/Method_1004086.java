@Override public void startup(final RuntimeData data) throws Exception {
  super.startup(data);
  Lookup.privateLookupIn(locator,Lookup.lookup()).defineClass(createClass(injectedClassName)).getField(FIELD_NAME).set(null,data);
}

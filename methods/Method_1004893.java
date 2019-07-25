public Properties apply(final Properties state,final Properties properties){
  if (null == state) {
    return properties;
  }
  propertiesTuple.setProperties(properties);
  stateTuple.setProperties(state);
  apply(stateTuple,propertiesTuple);
  return state;
}

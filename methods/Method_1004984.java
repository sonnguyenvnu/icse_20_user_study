public void merge(final StoreProperties properties){
  if (null != properties) {
    if (null != properties.getId() && null != getId() && !properties.getId().equals(getId())) {
      final String newId=getId() + "_" + properties.getId();
      properties.setId(newId);
      setId(newId);
    }
    props.putAll(properties.getProperties());
  }
}

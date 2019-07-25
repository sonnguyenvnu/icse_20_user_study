public Object build(DeserializationContext ctxt,PropertyValueBuffer buffer) throws IOException {
  Object bean=_valueInstantiator.createFromObjectWith(ctxt,_propertiesInOrder,buffer);
  if (bean != null) {
    bean=buffer.handleIdValue(ctxt,bean);
    for (PropertyValue pv=buffer.buffered(); pv != null; pv=pv.next) {
      pv.assign(bean);
    }
  }
  return bean;
}

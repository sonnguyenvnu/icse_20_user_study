@SuppressFBWarnings(value="BC_UNCONFIRMED_CAST",justification="If an element is not an Entity it must be an Edge") @Override public ExampleDomainObject _apply(final Element element){
  if (element instanceof Entity) {
    return new ExampleDomainObject(element.getGroup(),((Entity)element).getVertex());
  }
  return new ExampleDomainObject(element.getGroup(),((Edge)element).getSource(),((Edge)element).getDestination(),((Edge)element).isDirected());
}

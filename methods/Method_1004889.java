@Override public boolean test(final Element element,final Element element2){
  if (element == element2) {
    return true;
  }
  if (null == element || null == element2) {
    return false;
  }
  if (!element.getClass().equals(element2.getClass())) {
    return false;
  }
  if (!element.getGroup().equals(element2.getGroup())) {
    return false;
  }
  if (element instanceof Entity) {
    if (!((Entity)element).getVertex().equals(((Entity)element2).getVertex())) {
      return false;
    }
  }
 else {
    if (!((Edge)element).getSource().equals(((Edge)element2).getSource())) {
      return false;
    }
    if (!((Edge)element).getDestination().equals(((Edge)element2).getDestination())) {
      return false;
    }
    if (!((Edge)element).getDirectedType().equals(((Edge)element2).getDirectedType())) {
      return false;
    }
  }
  for (  final String key : groupByProperties) {
    if (!Objects.equals(element.getProperty(key),element2.getProperty(key))) {
      return false;
    }
  }
  return true;
}

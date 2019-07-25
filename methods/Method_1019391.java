private String typeof(Element element){
  if (element instanceof SoftwareSystem) {
    return "software system";
  }
 else {
    return element.getClass().getSimpleName().toLowerCase();
  }
}

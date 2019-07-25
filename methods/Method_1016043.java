/** 
 * Filter out elements for which the provided visitor returns true.
 * @param predicate
 */
public InjectorBuilder filter(ElementVisitor<Boolean> predicate){
  List<Element> elements=new ArrayList<Element>();
  for (  Element element : Elements.getElements(Stage.TOOL,module)) {
    if (element.acceptVisitor(predicate)) {
      elements.add(element);
    }
  }
  this.module=Elements.getModule(elements);
  return this;
}

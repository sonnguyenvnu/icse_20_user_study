/** 
 * @return whether the given element is found through (=matches) this expression 
 */
public boolean matches(Element element){
  Element.Type eleType=element.getType();
  boolean elementTypeMatches=false;
switch (eleType) {
case NODE:
    elementTypeMatches=elementsTypeFilters.contains(ElementsTypeFilter.NODES);
  break;
case WAY:
elementTypeMatches=elementsTypeFilters.contains(ElementsTypeFilter.WAYS);
break;
case RELATION:
elementTypeMatches=elementsTypeFilters.contains(ElementsTypeFilter.RELATIONS);
break;
}
return elementTypeMatches && tagExprRoot.matches(element);
}

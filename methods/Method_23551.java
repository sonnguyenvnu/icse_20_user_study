/** 
 * Used in place of element.getFloatAttribute(a) because we can have a unit suffix (length or coordinate).
 * @param element what to parse
 * @param attribute name of the attribute to get
 * @param relativeTo (float) Used for %. When relative to viewbox, shouldbe svgWidth for horizontal dimentions, svgHeight for vertical, and svgXYSize for anything else.
 * @return unit-parsed version of the data
 */
static protected float getFloatWithUnit(XML element,String attribute,float relativeTo){
  String val=element.getString(attribute);
  return (val == null) ? 0 : parseUnitSize(val,relativeTo);
}

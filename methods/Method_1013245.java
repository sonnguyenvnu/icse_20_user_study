/** 
 * August 2014 - TL A location element is prepannded to an implementing element
 */
public Element export(Document doc,tla2sany.xml.SymbolContext context){
  try {
    Element e=getSemanticElement(doc,context);
    try {
      Element loc=getLocationElement(doc);
      e.insertBefore(loc,e.getFirstChild());
    }
 catch (    UnsupportedOperationException uoe) {
      uoe.printStackTrace();
      throw uoe;
    }
catch (    RuntimeException ee) {
    }
    return e;
  }
 catch (  RuntimeException ee) {
    System.err.println("failed for node.toString(): " + toString() + "\n with error ");
    ee.printStackTrace();
    throw ee;
  }
}

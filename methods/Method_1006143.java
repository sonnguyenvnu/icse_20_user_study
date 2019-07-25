/** 
 * Declares the fields and entry variables. It has three arguments, each a (possibly empty) list of variable names. The three lists are of: fields, integer entry variables, and string entry variables. There is an additional field that BibTEX automatically declares, crossref, used for cross referencing. And there is an additional string entry variable automatically declared, sort.key$, used by the SORT command. Each of these variables has a value for each entry on the list.
 */
private void entry(Tree child){
  Tree t=child.getChild(0);
  for (int i=0; i < t.getChildCount(); i++) {
    String name=t.getChild(i).getText();
    for (    BstEntry entry : entries) {
      entry.getFields().put(name,null);
    }
  }
  t=child.getChild(1);
  for (int i=0; i < t.getChildCount(); i++) {
    String name=t.getChild(i).getText();
    for (    BstEntry entry : entries) {
      entry.localIntegers.put(name,0);
    }
  }
  t=child.getChild(2);
  for (int i=0; i < t.getChildCount(); i++) {
    String name=t.getChild(i).getText();
    for (    BstEntry entry : entries) {
      entry.localStrings.put(name,null);
    }
  }
  for (  BstEntry entry : entries) {
    entry.localStrings.put("sort.key$",null);
  }
}

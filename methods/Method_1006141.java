public String run(Collection<BibEntry> bibtex){
  bbl=new StringBuilder();
  strings=new HashMap<>();
  integers=new HashMap<>();
  integers.put("entry.max$",Integer.MAX_VALUE);
  integers.put("global.max$",Integer.MAX_VALUE);
  functions=new HashMap<>();
  functions.putAll(buildInFunctions);
  stack=new Stack<>();
  entries=new ArrayList<>(bibtex.size());
  ListIterator<BstEntry> listIter=entries.listIterator();
  for (  BibEntry entry : bibtex) {
    listIter.add(new BstEntry(entry));
  }
  for (int i=0; i < tree.getChildCount(); i++) {
    Tree child=tree.getChild(i);
switch (child.getType()) {
case BstParser.STRINGS:
      strings(child);
    break;
case BstParser.INTEGERS:
  integers(child);
break;
case BstParser.FUNCTION:
function(child);
break;
case BstParser.EXECUTE:
execute(child);
break;
case BstParser.SORT:
sort();
break;
case BstParser.ITERATE:
iterate(child);
break;
case BstParser.REVERSE:
reverse(child);
break;
case BstParser.ENTRY:
entry(child);
break;
case BstParser.READ:
read();
break;
case BstParser.MACRO:
macro(child);
break;
default :
LOGGER.info("Unknown type: " + child.getType());
break;
}
}
return bbl.toString();
}

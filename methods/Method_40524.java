/** 
 * Constructs a qualified name by appending  {@code name} to this scope's qname. <p><p/> The indexer uses globally unique fully qualified names to address identifier definition sites.  Many Python identifiers are already globally addressable using dot-separated package, class and attribute names. <p> <p/> Function variables and parameters are not globally addressable in the language, so the indexer uses a special path syntax for creating globally unique qualified names for them.  By convention the syntax is "@" for parameters and "&amp;" for local variables.
 * @param name a name to append to the current qname
 * @return the qname for {@code name}.  Does not change this scope's path.
 */
public String extendPath(String name){
  if (name.endsWith(".py")) {
    name=Util.moduleNameFor(name);
  }
  if (path.equals("")) {
    return name;
  }
  String sep;
switch (scopeType) {
case MODULE:
case CLASS:
case INSTANCE:
case SCOPE:
    sep=".";
  break;
case FUNCTION:
sep="&";
break;
default :
System.err.println("unsupported context for extendPath: " + scopeType);
return path;
}
return path + sep + name;
}

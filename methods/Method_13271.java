protected String resolveInternalTypeName(List<TerminalNode> identifiers){
switch (identifiers.size()) {
case 0:
    return null;
case 1:
  String name=identifiers.get(0).getText();
String qualifiedName=typeNameCache.get(name);
if (qualifiedName != null) {
return qualifiedName;
}
String imp=nameToInternalTypeName.get(name);
if (imp != null) {
return imp;
}
String prefix=name + '.';
if (entry.getPath().indexOf('/') != -1) {
Container.Entry parent=entry.getParent();
int packageLength=parent.getPath().length() + 1;
for (Container.Entry child : parent.getChildren()) {
if (!child.isDirectory() && child.getPath().substring(packageLength).startsWith(prefix)) {
qualifiedName=packageName + '/' + name;
typeNameCache.put(name,qualifiedName);
return qualifiedName;
}
}
}
for (Container.Entry child : entry.getContainer().getRoot().getChildren()) {
if (!child.isDirectory() && child.getPath().startsWith(prefix)) {
typeNameCache.put(name,name);
return name;
}
}
try {
if (Class.forName("java.lang." + name) != null) {
qualifiedName="java/lang/" + name;
typeNameCache.put(name,qualifiedName);
return qualifiedName;
}
}
 catch (ClassNotFoundException ignore) {
}
qualifiedName="*/" + name;
typeNameCache.put(name,qualifiedName);
return qualifiedName;
default :
return concatIdentifiers(identifiers);
}
}

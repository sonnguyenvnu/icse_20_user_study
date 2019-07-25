@Override public void append(QualifiedName type){
  if (type.getPackage().isEmpty() && type.isTopLevel()) {
    append(type.getSimpleName());
    return;
  }
  TypeUsage.Builder usage=new TypeUsage.Builder().start(source.length()).type(type).nullableScope(getLast(types));
  append(type.toString());
  usages.add(usage.end(source.length()).build());
}

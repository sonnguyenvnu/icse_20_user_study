static String asHumanDescription(Collection<? extends MemberViewBinding> bindings){
  Iterator<? extends MemberViewBinding> iterator=bindings.iterator();
switch (bindings.size()) {
case 1:
    return iterator.next().getDescription();
case 2:
  return iterator.next().getDescription() + " and " + iterator.next().getDescription();
default :
StringBuilder builder=new StringBuilder();
for (int i=0, count=bindings.size(); i < count; i++) {
if (i != 0) {
  builder.append(", ");
}
if (i == count - 1) {
  builder.append("and ");
}
builder.append(iterator.next().getDescription());
}
return builder.toString();
}
}

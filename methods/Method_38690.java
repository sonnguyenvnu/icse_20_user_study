/** 
 * Parses arrays, once when open bracket has been consumed.
 */
protected Object parseArrayContent(Class targetType,Class componentType){
  if (targetType == Object.class) {
    targetType=List.class;
  }
  targetType=replaceWithMappedTypeForPath(targetType);
  if (componentType == null && targetType != null && targetType.isArray()) {
    componentType=targetType.getComponentType();
  }
  path.push(VALUES);
  componentType=replaceWithMappedTypeForPath(componentType);
  Collection<Object> target=newArrayInstance(targetType);
  boolean koma=false;
  mainloop:   while (true) {
    skipWhiteSpaces();
    char c=input[ndx];
    if (c == ']') {
      if (koma) {
        syntaxError("Trailing comma");
      }
      ndx++;
      path.pop();
      return target;
    }
    Object value=parseValue(componentType,null,null);
    target.add(value);
    skipWhiteSpaces();
    c=input[ndx];
switch (c) {
case ']':
      ndx++;
    break mainloop;
case ',':
  ndx++;
koma=true;
break;
default :
syntaxError("Invalid char: expected ] or ,");
}
}
path.pop();
if (targetType != null) {
return convertType(target,targetType);
}
return target;
}

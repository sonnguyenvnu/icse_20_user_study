static AttributeInfo lookup(List<AttributeInfo> attributes,String name){
  if (attributes == null)   return null;
  for (  AttributeInfo ai : attributes)   if (ai.getName().equals(name))   return ai;
  return null;
}

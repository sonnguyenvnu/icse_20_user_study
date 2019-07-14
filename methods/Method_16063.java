private static String createCopierCode(Class source,Class target){
  Map<String,ClassProperty> sourceProperties=null;
  Map<String,ClassProperty> targetProperties=null;
  if (Map.class.isAssignableFrom(source)) {
    if (!Map.class.isAssignableFrom(target)) {
      targetProperties=createProperty(target);
      sourceProperties=createMapProperty(targetProperties);
    }
  }
 else   if (Map.class.isAssignableFrom(target)) {
    if (!Map.class.isAssignableFrom(source)) {
      sourceProperties=createProperty(source);
      targetProperties=createMapProperty(sourceProperties);
    }
  }
 else {
    targetProperties=createProperty(target);
    sourceProperties=createProperty(source);
  }
  if (sourceProperties == null || targetProperties == null) {
    throw new UnsupportedOperationException("??????,source:" + source + " target:" + target);
  }
  StringBuilder code=new StringBuilder();
  for (  ClassProperty sourceProperty : sourceProperties.values()) {
    ClassProperty targetProperty=targetProperties.get(sourceProperty.getName());
    if (targetProperty == null) {
      continue;
    }
    code.append("if(!ignore.contains(\"").append(sourceProperty.getName()).append("\")){\n\t");
    if (!sourceProperty.isPrimitive()) {
      code.append("if(source.").append(sourceProperty.getReadMethod()).append("!=null){\n");
    }
    code.append(targetProperty.generateVar(targetProperty.getName())).append("=").append(sourceProperty.generateGetter(target,targetProperty.getType())).append(";\n");
    if (!targetProperty.isPrimitive()) {
      code.append("\tif(").append(sourceProperty.getName()).append("!=null){\n");
    }
    code.append("\ttarget.").append(targetProperty.generateSetter(targetProperty.getType(),sourceProperty.getName())).append(";\n");
    if (!targetProperty.isPrimitive()) {
      code.append("\t}\n");
    }
    if (!sourceProperty.isPrimitive()) {
      code.append("\t}\n");
    }
    code.append("}\n");
  }
  return code.toString();
}

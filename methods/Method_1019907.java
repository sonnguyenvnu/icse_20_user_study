@Override public boolean process(Set<? extends TypeElement> annotations,RoundEnvironment roundEnv){
  Set<? extends Element> adapterFactories=roundEnv.getElementsAnnotatedWith(GsonTypeAdapterFactory.class);
  if (adapterFactories.isEmpty()) {
    return false;
  }
  Set<? extends Element> autoValueElements=roundEnv.getElementsAnnotatedWith(AutoValue.class);
  List<TypeElement> elements=autoValueElements.stream().map(e -> (TypeElement)e).filter(e -> extension.applicable(new LimitedContext(processingEnv,(TypeElement)e))).sorted((o1,o2) -> {
    final String o1Name=classNameOf((TypeElement)o1,".");
    final String o2Name=classNameOf((TypeElement)o2,".");
    return o1Name.compareTo(o2Name);
  }
).collect(Collectors.toList());
  if (elements.isEmpty()) {
    Element reportableElement=adapterFactories.iterator().next();
    if (!autoValueElements.isEmpty()) {
      processingEnv.getMessager().printMessage(ERROR,"Failed to write TypeAdapterFactory: Cannot generate class for this " + "@GsonTypeAdapterFactory-annotated element because while @AutoValue-annotated " + "elements were found on the compilation classpath, none of them contain a " + "requisite public static TypeAdapter-returning signature method to opt in to " + "being included in @GsonTypeAdapterFactory-generated factories. See the " + "auto-value-gson README for more information on declaring these.",reportableElement);
    }
 else {
      processingEnv.getMessager().printMessage(ERROR,"Failed to write TypeAdapterFactory: Cannot generate class for this " + "@GsonTypeAdapterFactory-annotated element because no @AutoValue-annotated " + "elements were found on the compilation classpath.",reportableElement);
    }
    return false;
  }
  for (  Element element : adapterFactories) {
    if (!element.getModifiers().contains(ABSTRACT)) {
      error(element,"Must be abstract!");
    }
    TypeElement type=(TypeElement)element;
    if (!implementsTypeAdapterFactory(type)) {
      error(element,"Must implement TypeAdapterFactory!");
    }
    String adapterName=classNameOf(type,"_");
    String qualifiedName=classNameOf(type,".");
    PackageElement packageElement=packageElementOf(type);
    String packageName=packageElement.getQualifiedName().toString();
    List<TypeElement> applicableElements=elements.stream().filter(e -> {
      Visibility typeVisibility=Visibility.ofElement(e);
switch (typeVisibility) {
case PRIVATE:
        return false;
case DEFAULT:
case PROTECTED:
      if (!getPackage(e).equals(packageElement)) {
        return false;
      }
    break;
}
ExecutableElement adapterMethod=getTypeAdapterMethod(e);
if (adapterMethod == null) {
  return false;
}
Visibility methodVisibility=Visibility.ofElement(adapterMethod);
switch (methodVisibility) {
case PRIVATE:
  return false;
case DEFAULT:
case PROTECTED:
if (!getPackage(adapterMethod).equals(packageElement)) {
  return false;
}
break;
}
return true;
}
).collect(toList());
TypeSpec typeAdapterFactory=createTypeAdapterFactory(type,applicableElements,packageName,adapterName,qualifiedName);
JavaFile file=JavaFile.builder(packageName,typeAdapterFactory).build();
try {
file.writeTo(processingEnv.getFiler());
}
 catch (IOException e) {
processingEnv.getMessager().printMessage(ERROR,"Failed to write TypeAdapterFactory: " + e.getLocalizedMessage(),element);
}
}
return false;
}

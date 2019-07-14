private Map<TypeElement,BindingSet> findAndParseTargets(RoundEnvironment env){
  Map<TypeElement,BindingSet.Builder> builderMap=new LinkedHashMap<>();
  Set<TypeElement> erasedTargetNames=new LinkedHashSet<>();
  for (  Element element : env.getElementsAnnotatedWith(BindAnim.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceAnimation(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindAnim.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindArray.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceArray(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindArray.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindBitmap.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceBitmap(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindBitmap.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindBool.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceBool(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindBool.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindColor.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceColor(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindColor.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindDimen.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceDimen(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindDimen.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindDrawable.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceDrawable(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindDrawable.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindFloat.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceFloat(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindFloat.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindFont.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceFont(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindFont.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindInt.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceInt(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindInt.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindString.class)) {
    if (!SuperficialValidation.validateElement(element))     continue;
    try {
      parseResourceString(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindString.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindView.class)) {
    try {
      parseBindView(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindView.class,e);
    }
  }
  for (  Element element : env.getElementsAnnotatedWith(BindViews.class)) {
    try {
      parseBindViews(element,builderMap,erasedTargetNames);
    }
 catch (    Exception e) {
      logParsingError(element,BindViews.class,e);
    }
  }
  for (  Class<? extends Annotation> listener : LISTENERS) {
    findAndParseListener(env,listener,builderMap,erasedTargetNames);
  }
  Deque<Map.Entry<TypeElement,BindingSet.Builder>> entries=new ArrayDeque<>(builderMap.entrySet());
  Map<TypeElement,BindingSet> bindingMap=new LinkedHashMap<>();
  while (!entries.isEmpty()) {
    Map.Entry<TypeElement,BindingSet.Builder> entry=entries.removeFirst();
    TypeElement type=entry.getKey();
    BindingSet.Builder builder=entry.getValue();
    TypeElement parentType=findParentType(type,erasedTargetNames);
    if (parentType == null) {
      bindingMap.put(type,builder.build());
    }
 else {
      BindingSet parentBinding=bindingMap.get(parentType);
      if (parentBinding != null) {
        builder.setParent(parentBinding);
        bindingMap.put(type,builder.build());
      }
 else {
        entries.addLast(entry);
      }
    }
  }
  return bindingMap;
}

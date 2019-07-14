private static TagModel newTagModel(Element typeElement,Types types){
  return new TagModel(ClassName.bestGuess(typeElement.toString()),types.directSupertypes(typeElement.asType()).size() > 1,!typeElement.getEnclosedElements().isEmpty(),typeElement);
}

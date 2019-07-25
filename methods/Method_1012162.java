private static void write(Element element,String name,String path){
  Element libraryElement=new Element(LIBRARY_TAG);
  libraryElement.setAttribute(LIBRARY_NAME_TAG,name);
  libraryElement.setAttribute(LIBRARY_PATH_TAG,path);
  element.addContent(libraryElement);
}

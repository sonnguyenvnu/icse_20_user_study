private String getUnqualifiedClassName(String goodAnnotation){
  return goodAnnotation.substring(goodAnnotation.lastIndexOf(".") + 1);
}

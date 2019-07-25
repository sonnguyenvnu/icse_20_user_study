/** 
 * Instantiate an annotation parameter value.
 * @param annotationClassInfo the annotation class info
 * @return the instance
 */
Object instantiate(final ClassInfo annotationClassInfo){
  return value.instantiateOrGet(annotationClassInfo,name);
}

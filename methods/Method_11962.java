/** 
 * @return a copy of this description, with no children (on the assumption that some of thechildren will be added back)
 */
public Description childlessCopy(){
  return new Description(fTestClass,fDisplayName,fAnnotations);
}

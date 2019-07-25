/** 
 * @param item Annotation.
 * @return Text of annotation.
 */
public static TextHBox annotation(AnnotationNode item){
  TextHBox t=new TextHBox();
  annotation(t,item);
  return t;
}

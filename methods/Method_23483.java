/** 
 * ( begin auto-generated from PShape_getChild.xml ) Extracts a child shape from a parent shape. Specify the name of the shape with the <b>target</b> parameter. The shape is returned as a <b>PShape</b> object, or <b>null</b> is returned if there is an error. ( end auto-generated )
 * @webref pshape:method
 * @usage web_application
 * @brief Returns a child element of a shape as a PShape object
 * @param index the layer position of the shape to get
 * @see PShape#addChild(PShape)
 */
public PShape getChild(int index){
  crop();
  return children[index];
}

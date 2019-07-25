/** 
 * Create a new instance to bake the transform with.
 * @param original the original clipboard
 * @param transform the transform
 * @return a builder
 */
public static FlattenedClipboardTransform transform(Clipboard original,Transform transform){
  return new FlattenedClipboardTransform(original,transform);
}

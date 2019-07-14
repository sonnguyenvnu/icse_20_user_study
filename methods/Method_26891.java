/** 
 * Update the target's data to match the source. Before calling this, make sure canMorph(target, source) is true.
 * @param target The target path represented in an array of PathDataNode
 * @param source The source path represented in an array of PathDataNode
 */
public static void updateNodes(@NonNull PathDataNode[] target,@NonNull PathDataNode[] source){
  for (int i=0; i < source.length; i++) {
    target[i].mType=source[i].mType;
    for (int j=0; j < source[i].mParams.length; j++) {
      target[i].mParams[j]=source[i].mParams[j];
    }
  }
}

/** 
 * Returns path value.
 */
public String path(){
  if (methref != null) {
    final String methodName=methref.ref();
    return target.getName() + '#' + methodName;
  }
  return path;
}

/** 
 * Return the checked tag index.
 * @return the checked tag index, or -1 if not exists.
 */
protected int getCheckedTagIndex(){
  final int count=getChildCount();
  for (int i=0; i < count; i++) {
    final TagView tag=getTagAt(i);
    if (tag.isChecked) {
      return i;
    }
  }
  return -1;
}

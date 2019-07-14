/** 
 * Check to see if an index provided for within a section is valid
 * @param keyString the key of the section component
 * @param index the index in the section component
 * @return if the index is valid for that section component
 */
public static boolean isSectionIndexValid(SectionContext c,String keyString,int index){
  final Section scopedSection=c.getSectionScope();
  final SectionTree sectionTree=c.getSectionTree();
  if (scopedSection == null || sectionTree == null) {
    return false;
  }
  final String globalKey=scopedSection.getGlobalKey() + keyString;
  return sectionTree.isSectionIndexValid(globalKey,index);
}

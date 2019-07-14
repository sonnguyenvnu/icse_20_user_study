/** 
 * Scroll to the index in the section with the given key. 
 */
public static void requestFocus(SectionContext c,String sectionKey,FocusType focusType){
  final Section scopedSection=c.getSectionScope();
  final SectionTree sectionTree=c.getSectionTree();
  if (scopedSection == null || sectionTree == null) {
    return;
  }
  final String globalKey=scopedSection.getGlobalKey() + sectionKey;
switch (focusType) {
case START:
    sectionTree.requestFocusStart(globalKey);
  break;
case END:
sectionTree.requestFocusEnd(globalKey);
break;
}
}

/** 
 * Scroll to the index in the section with the given key and an additional offset. One notable difference between this and the  {@link #requestFocus(SectionContext,String)} API is that thiswill *always* take an action, while  {@link #requestFocus(SectionContext,String)} will ignorethe command if the item is visible on the screen.
 * @param sectionKey the key of the section component.
 * @param index the index the section component
 * @param offset distance, in pixels, from the top of the screen to scroll the requested item.
 */
public static void requestFocusWithOffset(SectionContext c,String sectionKey,int index,int offset){
  final Section scopedSection=c.getSectionScope();
  final SectionTree sectionTree=c.getSectionTree();
  if (scopedSection == null || sectionTree == null) {
    return;
  }
  final String globalKey=scopedSection.getGlobalKey() + sectionKey;
  sectionTree.requestFocusWithOffset(globalKey,index,offset);
}

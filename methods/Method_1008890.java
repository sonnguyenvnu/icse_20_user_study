/** 
 * @param paragraphs
 * @param remediate
 * @return
 * @throws IOException 
 * @throws Exception
 */
public BookmarksStatus check(List<Object> contents,boolean remediate) throws IOException {
  List<Object> faulty=inspectBookmarks(contents);
  if (remediate) {
    for (    Object o : faulty) {
      if (o instanceof CTBookmark) {
        CTBookmark start=(CTBookmark)o;
        Object parent=start.getParent();
        if (parent instanceof ContentAccessor) {
          if (remove(((ContentAccessor)parent).getContent(),o)) {
          }
 else {
            write("FIXME Couldn't find start " + start.getName() + start.getId().intValue());
            log.warn("FIXME Couldn't find start " + start.getName() + start.getId().intValue());
          }
        }
 else {
          log.warn("TODO: handle parent:" + parent.getClass().getName());
          write("TODO: handle parent:" + parent.getClass().getName());
        }
      }
      if (o instanceof CTMarkupRange && (!(o instanceof CTBookmark))) {
        CTMarkupRange end=(CTMarkupRange)o;
        Object parent=end.getParent();
        if (parent instanceof ContentAccessor) {
          if (remove(((ContentAccessor)parent).getContent(),o)) {
          }
 else {
            write("FIXME Couldn't find end " + end.getId().longValue());
            log.warn("FIXME Couldn't find end " + end.getId().longValue());
          }
        }
 else {
          log.warn("TODO: handle parent:" + parent.getClass().getName());
          write("TODO: handle parent:" + parent.getClass().getName());
        }
      }
    }
  }
  if (faulty.size() == 0) {
    log.debug("Nothing to fix");
    write("Nothing to fix");
    return BookmarksStatus.OK;
  }
 else   if (remediate) {
    return BookmarksStatus.FIXED;
  }
 else {
    return BookmarksStatus.BROKEN;
  }
}

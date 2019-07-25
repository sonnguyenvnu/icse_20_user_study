/** 
 * ??
 * @param model
 * @return
 */
public boolean insert(ArticleWithBLOBs model) throws MyException {
  if (model == null) {
    return false;
  }
  if (model.getCanDelete() == null) {
    model.setCanDelete(CanDeleteEnum.CAN.getCanDelete());
  }
  if (model.getMarkdown() == null) {
    model.setMarkdown("");
  }
  if (model.getContent() == null) {
    model.setContent("");
  }
  if (model.getStatus() == null) {
    model.setStatus(ArticleStatus.COMMON.getStatus());
  }
  if (!ArticleStatus.PAGE.getStatus().equals(model.getStatus())) {
    model.setMkey(null);
  }
  return super.insert(model);
}

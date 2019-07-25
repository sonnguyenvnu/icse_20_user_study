/** 
 * Updates with  {@link UpdateOptions}.
 */
public void update(UpdateOptions updateOptions){
  updateOptions.feedbackResponseIdOption.ifPresent(s -> feedbackResponseId=s);
  updateOptions.commentTextOption.ifPresent(s -> commentText=s);
  updateOptions.showCommentToOption.ifPresent(s -> showCommentTo=s);
  updateOptions.showGiverNameToOption.ifPresent(s -> showGiverNameTo=s);
  updateOptions.lastEditorEmailOption.ifPresent(s -> lastEditorEmail=s);
  updateOptions.lastEditedAtOption.ifPresent(s -> lastEditedAt=s);
  updateOptions.giverSectionOption.ifPresent(s -> giverSection=s);
  updateOptions.receiverSectionOption.ifPresent(s -> receiverSection=s);
}

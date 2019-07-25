/** 
 * Collect and upload all changes made by the user 
 */
public void upload(){
  context.startService(new Intent(context,QuestChangesUploadService.class));
}

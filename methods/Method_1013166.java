/** 
 * Commit the page
 */
public void commit(boolean onSave){
  IManagedForm managedForm=getManagedForm();
  if (managedForm != null) {
    managedForm.commit(onSave);
  }
}

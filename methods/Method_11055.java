/** 
 * ??????? ??
 */
public static void loadingHttpCancel(String reminder){
  if (mDialogLoading != null) {
    mDialogLoading.cancel(reminder);
  }
}

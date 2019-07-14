/** 
 * ???
 * @param hint ?????
 */
public static void initDialogExport(final Context mContext,final String hint){
  RxVibrateTool.vibrateOnce(mContext,150);
  final RxDialogSureCancel mDialogExport=new RxDialogSureCancel(mContext,R.style.PushUpInDialogThem);
  mDialogExport.getTitleView().setBackground(null);
  mDialogExport.getTitleView().setText("??????");
  mDialogExport.setContent(hint);
  mDialogExport.getContentView().setTextSize(13f);
  mDialogExport.getSureView().setVisibility(View.GONE);
  mDialogExport.setCancel("??");
  mDialogExport.setCancelListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      RxVibrateTool.vibrateOnce(mContext,150);
      mDialogExport.cancel();
    }
  }
);
  mDialogExport.setCancelable(false);
  mDialogExport.show();
}

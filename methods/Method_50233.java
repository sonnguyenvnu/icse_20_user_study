/** 
 * ???????- onActivityResult????????RxGalleryFinalApi.cropActivityForResult??????? RxGalleryFinalApi.cropActivityForResult()
 */
public static void cropScannerForResult(Activity context,String outPPath,String inputPath){
  if (TextUtils.isEmpty(inputPath)) {
    Logger.e("-??????-");
    return;
  }
  Uri outUri=Uri.fromFile(new File(outPPath));
  Uri inputUri=Uri.fromFile(new File(inputPath));
  Intent intent=new Intent(context,UCropActivity.class);
  Bundle bundle=new Bundle();
  bundle.putParcelable(UCrop.EXTRA_OUTPUT_URI,outUri);
  bundle.putParcelable(UCrop.EXTRA_INPUT_URI,inputUri);
  cropImagePath=new File(outUri.getPath());
  Logger.i("???" + outUri.getPath());
  Logger.i("???" + inputUri.getPath());
  intent.putExtras(bundle);
  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  context.startActivityForResult(intent,-1);
}

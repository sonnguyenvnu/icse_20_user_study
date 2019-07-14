/** 
 * ?????????--????
 */
public static void cropActivityForResult(Activity context,MediaScanner.ScanCallback imgScanner){
  if (cropImagePath != null) {
    MediaScanner scanner=new MediaScanner(context);
    scanner.scanFile(RxGalleryFinalApi.cropImagePath.getPath(),IMG_TYPE,imgScanner);
  }
}

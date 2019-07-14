/** 
 * ?????????--????
 * @param path ??
 */
public static void cropActivityForResult(Activity context,String path,MediaScanner.ScanCallback imgScanner){
  if (cropImagePath != null) {
    MediaScanner scanner=new MediaScanner(context);
    scanner.scanFile(path.trim(),IMG_TYPE,imgScanner);
  }
}

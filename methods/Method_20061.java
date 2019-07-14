/** 
 * Reads label list from Assets.
 */
private List<String> loadLabelList(Context context){
  List<String> labelList=new ArrayList<>();
  try (BufferedReader reader=new BufferedReader(new InputStreamReader(context.getAssets().open(LABEL_PATH)))){
    String line;
    while ((line=reader.readLine()) != null) {
      labelList.add(line);
    }
  }
 catch (  IOException e) {
    Log.e(TAG,"Failed to read label list.",e);
  }
  return labelList;
}

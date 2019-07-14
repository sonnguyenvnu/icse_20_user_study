private String getText(int filesInFolder,long length,boolean loading){
  String numOfItems=(filesInFolder != 0 ? filesInFolder + " " : "") + context.getResources().getQuantityString(R.plurals.items,filesInFolder);
  return numOfItems + "; " + (loading ? ">" : "") + Formatter.formatFileSize(context,length);
}

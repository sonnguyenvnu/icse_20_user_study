public void mkDir(final HybridFile path,final MainFragment ma){
  final Toast toast=Toast.makeText(ma.getActivity(),ma.getString(R.string.creatingfolder),Toast.LENGTH_SHORT);
  toast.show();
  Operations.mkdir(path,ma.getActivity(),mainActivity.isRootExplorer(),new Operations.ErrorCallBack(){
    @Override public void exists(    final HybridFile file){
      ma.getActivity().runOnUiThread(() -> {
        if (toast != null)         toast.cancel();
        Toast.makeText(mainActivity,mainActivity.getString(R.string.fileexist),Toast.LENGTH_SHORT).show();
        if (ma != null && ma.getActivity() != null) {
          mkdir(file.getMode(),file.getParent(),ma);
        }
      }
);
    }
    @Override public void launchSAF(    HybridFile file){
      if (toast != null)       toast.cancel();
      ma.getActivity().runOnUiThread(() -> {
        mainActivity.oppathe=path.getPath();
        mainActivity.operation=DataUtils.NEW_FOLDER;
        guideDialogForLEXA(mainActivity.oppathe);
      }
);
    }
    @Override public void launchSAF(    HybridFile file,    HybridFile file1){
    }
    @Override public void done(    HybridFile hFile,    final boolean b){
      ma.getActivity().runOnUiThread(() -> {
        if (b) {
          ma.updateList();
        }
 else {
          Toast.makeText(ma.getActivity(),ma.getString(R.string.operationunsuccesful),Toast.LENGTH_SHORT).show();
        }
      }
);
    }
    @Override public void invalidName(    final HybridFile file){
      ma.getActivity().runOnUiThread(() -> {
        if (toast != null)         toast.cancel();
        Toast.makeText(ma.getActivity(),ma.getString(R.string.invalid_name) + ": " + file.getName(),Toast.LENGTH_LONG).show();
      }
);
    }
  }
);
}

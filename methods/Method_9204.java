private void fillPresets(){
  presets.clear();
  presets.add(lowPreset);
  presets.add(mediumPreset);
  presets.add(highPreset);
  if (!typePreset.equals(lowPreset) && !typePreset.equals(mediumPreset) && !typePreset.equals(highPreset)) {
    presets.add(typePreset);
  }
  Collections.sort(presets,(o1,o2) -> {
    int index1=DownloadController.typeToIndex(DownloadController.AUTODOWNLOAD_TYPE_VIDEO);
    int index2=DownloadController.typeToIndex(DownloadController.AUTODOWNLOAD_TYPE_DOCUMENT);
    boolean video1=false;
    boolean doc1=false;
    for (int a=0; a < o1.mask.length; a++) {
      if ((o1.mask[a] & DownloadController.AUTODOWNLOAD_TYPE_VIDEO) != 0) {
        video1=true;
      }
      if ((o1.mask[a] & DownloadController.AUTODOWNLOAD_TYPE_DOCUMENT) != 0) {
        doc1=true;
      }
      if (video1 && doc1) {
        break;
      }
    }
    boolean video2=false;
    boolean doc2=false;
    for (int a=0; a < o2.mask.length; a++) {
      if ((o2.mask[a] & DownloadController.AUTODOWNLOAD_TYPE_VIDEO) != 0) {
        video2=true;
      }
      if ((o2.mask[a] & DownloadController.AUTODOWNLOAD_TYPE_DOCUMENT) != 0) {
        doc2=true;
      }
      if (video2 && doc2) {
        break;
      }
    }
    int size1=(video1 ? o1.sizes[index1] : 0) + (doc1 ? o1.sizes[index2] : 0);
    int size2=(video2 ? o2.sizes[index1] : 0) + (doc2 ? o2.sizes[index2] : 0);
    if (size1 > size2) {
      return 1;
    }
 else     if (size1 < size2) {
      return -1;
    }
    return 0;
  }
);
  if (listView != null) {
    RecyclerView.ViewHolder holder=listView.findViewHolderForAdapterPosition(usageProgressRow);
    if (holder != null) {
      holder.itemView.requestLayout();
    }
 else {
      listAdapter.notifyItemChanged(usageProgressRow);
    }
  }
  if (currentPresetNum == 0 || currentPresetNum == 3 && typePreset.equals(lowPreset)) {
    selectedPreset=presets.indexOf(lowPreset);
  }
 else   if (currentPresetNum == 1 || currentPresetNum == 3 && typePreset.equals(mediumPreset)) {
    selectedPreset=presets.indexOf(mediumPreset);
  }
 else   if (currentPresetNum == 2 || currentPresetNum == 3 && typePreset.equals(highPreset)) {
    selectedPreset=presets.indexOf(highPreset);
  }
 else {
    selectedPreset=presets.indexOf(typePreset);
  }
}

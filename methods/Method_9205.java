private void updateRows(){
  rowCount=0;
  autoDownloadRow=rowCount++;
  autoDownloadSectionRow=rowCount++;
  if (typePreset.enabled) {
    usageHeaderRow=rowCount++;
    usageProgressRow=rowCount++;
    usageSectionRow=rowCount++;
    typeHeaderRow=rowCount++;
    photosRow=rowCount++;
    videosRow=rowCount++;
    filesRow=rowCount++;
    typeSectionRow=rowCount++;
  }
 else {
    usageHeaderRow=-1;
    usageProgressRow=-1;
    usageSectionRow=-1;
    typeHeaderRow=-1;
    photosRow=-1;
    videosRow=-1;
    filesRow=-1;
    typeSectionRow=-1;
  }
}

@SuppressLint("DefaultLocale") private void setExclusionList(String exclusionList){
  mExclusionList=exclusionList;
  if (mExclusionList == null) {
    mParsedExclusionList=new String[0];
  }
 else {
    String splitExclusionList[]=exclusionList.toLowerCase().split(",");
    mParsedExclusionList=new String[splitExclusionList.length * 2];
    for (int i=0; i < splitExclusionList.length; i++) {
      String s=splitExclusionList[i].trim();
      if (s.startsWith("."))       s=s.substring(1);
      mParsedExclusionList[i * 2]=s;
      mParsedExclusionList[(i * 2) + 1]="." + s;
    }
  }
}

public void setXAxisCategories(List<Object> xAxisCategories,int totalLabels,int rotation,int y){
  putXAxis(ChartKeysUtil.XAxisKey.CATEGORIES.getKey(),xAxisCategories);
  if (xAxisCategories.size() >= totalLabels) {
    Map<String,Object> m=null;
    if (this.getxAxis().containsKey(ChartKeysUtil.XAxisKey.LABELS.getKey())) {
      m=(Map<String,Object>)this.getxAxis().get(ChartKeysUtil.XAxisKey.LABELS.getKey());
    }
    if (m == null) {
      m=new LinkedHashMap<String,Object>();
    }
    m.put(ChartKeysUtil.XAxisKey.LABELS_STEP.getKey(),xAxisCategories.size() / totalLabels + 1);
    m.put(ChartKeysUtil.XAxisKey.LABELS_ROTATION.getKey(),rotation);
    m.put(ChartKeysUtil.XAxisKey.LABELS_Y.getKey(),y);
    m.put(ChartKeysUtil.XAxisKey.MAX_STAGGER_LINES.getKey(),1);
    putXAxis(ChartKeysUtil.XAxisKey.LABELS.getKey(),m);
  }
}

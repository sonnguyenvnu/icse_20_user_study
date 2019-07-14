private void innerAddAdjuster(Adjuster adjuster){
  adjusterList.add(adjuster);
  adjuster.attach(this);
  postInvalidate();
}

@Override public void render(ElementTemplate eleTemplate,Object renderData,XWPFTemplate template){
  RunTemplate runTemplate=(RunTemplate)eleTemplate;
  XWPFRun run=runTemplate.getRun();
  run.setText(runTemplate.getSource(),0);
}

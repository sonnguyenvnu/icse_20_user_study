/** 
 * Fill data into the view This includes loading expressions into the trace explorer table.
 * @param modelName name of the model displayed in the view title section
 * @param problems a list of  {@link TLCError} objects representing the errors.
 */
protected void fill(Model model,List<TLCError> problems,final List<String> serializedInput){
  traceExplorerComposite.getTableViewer().setInput(new Vector<Formula>());
  FormHelper.setSerializedInput(traceExplorerComposite.getTableViewer(),serializedInput);
  TLCError trace=null;
  if (problems != null && !problems.isEmpty()) {
    StringBuffer buffer=new StringBuffer();
    for (int i=0; i < problems.size(); i++) {
      TLCError error=problems.get(i);
      appendError(buffer,error);
      if (error.hasTrace()) {
        Assert.isTrue(trace == null,"Two traces are provided. Unexpected. This is a bug");
        trace=error;
      }
    }
    if (trace == null) {
      trace=new TLCError();
    }
    final IDocument document=errorViewer.getDocument();
    try {
      document.replace(0,document.getLength(),buffer.toString());
      TLCUIHelper.setTLCLocationHyperlinks(errorViewer.getTextWidget());
    }
 catch (    BadLocationException e) {
      TLCUIActivator.getDefault().logError("Error reporting the error " + buffer.toString(),e);
    }
    TLCError oldTrace=(TLCError)variableViewer.getInput();
    boolean isNewTrace=trace != null && oldTrace != null && !(trace == oldTrace);
    if (isNewTrace) {
      this.setTraceInput(trace);
    }
    if (model.isSnapshot()) {
      final String date=sdf.format(model.getSnapshotTimeStamp());
      this.form.setText(model.getSnapshotFor().getName() + " (" + date + ")");
    }
 else {
      this.form.setText(model.getName());
    }
  }
 else {
    clear();
  }
  traceExplorerComposite.changeButtonEnablement();
}

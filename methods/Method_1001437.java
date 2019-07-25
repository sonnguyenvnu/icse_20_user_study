/** 
 * Connect the specified  {@link DataPlot} with this instance.<p> If this <tt>Plot</tt> instance is already connected with a  <tt>DataPlot</tt> the connection will be released and a {@link PlotEvent} of the type {@link PlotEventType#DATA_PLOT_DISCONNECTED}will be sent to all registrated  {@link PlotListener PlotListeners}. <p> It registers itself at <tt>dataPlot</tt> and all its  {@link DataCurve DataCurves}. <p> Finally all curves will be generated and a <tt>PlotEvent</tt> of the type  {@link PlotEventType#DATA_PLOT_CONNECTED} will be transmitted.
 * @param dataPlot Data to be connected with this plot instance.Can be <tt>null</tt> in order to disconnect this instance from any <tt>DataPlot</tt>.
 */
public void connect(DataPlot dataPlot){
  if (_dataPlot != null) {
    _dataPlot.removeDataListener(this);
    notifyListeners(new PlotEvent(this,PlotEventType.DATA_PLOT_DISCONNECTED,_dataPlot));
  }
  _dataPlot=dataPlot;
  if (_dataPlot != null) {
    _dataPlot.addDataListener(this);
    generateCurves(_dataPlot);
    notifyListeners(new PlotEvent(this,PlotEventType.DATA_PLOT_CONNECTED,_dataPlot));
  }
}

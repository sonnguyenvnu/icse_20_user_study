/** 
 * Connects the wrapped  {@link Plot} instance with the specified{@link DataPlot}.
 * @param dataPlot Data to be connected with this plot canvas. Can be <tt>null</tt> in order to disconnect this instance from a <tt>DataPlot</tt>.
 */
public void connect(DataPlot dataPlot){
  _plot.connect(dataPlot);
}

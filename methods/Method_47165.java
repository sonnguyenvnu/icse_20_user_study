/** 
 * Creates an instance for  {@link LineDataSet} which will store the entries
 */
private LineDataSet createDataSet(){
  LineDataSet lineDataset=new LineDataSet(new ArrayList<Entry>(),null);
  lineDataset.setLineWidth(1.75f);
  lineDataset.setCircleRadius(5f);
  lineDataset.setCircleHoleRadius(2.5f);
  lineDataset.setColor(Color.WHITE);
  lineDataset.setCircleColor(Color.WHITE);
  lineDataset.setHighLightColor(Color.WHITE);
  lineDataset.setDrawValues(false);
  lineDataset.setCircleColorHole(accentColor);
  return lineDataset;
}

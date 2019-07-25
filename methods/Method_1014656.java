private void calculate(List<?> categories,Series.DataType axisType){
  double tickSpace=styler.getPlotContentSize() * workingSpace;
  double margin=Utils.getTickStartOffset(workingSpace,tickSpace);
  double gridStep=(tickSpace / categories.size());
  double firstPosition=gridStep / 2.0;
  if (axisType == Series.DataType.String) {
    axisFormat=new StringFormatter();
  }
 else   if (axisType == Series.DataType.Number) {
    axisFormat=new NumberFormatter(styler,axisDirection,minValue,maxValue);
  }
 else   if (axisType == Series.DataType.Date) {
    if (styler.getDatePattern() == null) {
      throw new RuntimeException("You need to set the Date Formatting Pattern!!!");
    }
    SimpleDateFormat simpleDateformat=new SimpleDateFormat(styler.getDatePattern(),styler.getLocale());
    simpleDateformat.setTimeZone(styler.getTimezone());
    axisFormat=simpleDateformat;
  }
  int counter=0;
  for (  Object category : categories) {
    if (axisType == Series.DataType.String) {
      tickLabels.add(category.toString());
    }
 else     if (axisType == Series.DataType.Number) {
      tickLabels.add(axisFormat.format(new BigDecimal(category.toString()).doubleValue()));
    }
 else     if (axisType == Series.DataType.Date) {
      tickLabels.add(axisFormat.format((((Date)category).getTime())));
    }
    double tickLabelPosition=margin + firstPosition + gridStep * counter++;
    tickLocations.add(tickLabelPosition);
  }
}

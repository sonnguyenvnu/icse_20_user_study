/** 
 * Set a  {@linkplain com.prolificinteractive.materialcalendarview.format.TitleFormatter}using the provided month labels
 * @param monthLabels month labels to use
 * @see com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
 * @see #setTitleFormatter(com.prolificinteractive.materialcalendarview.format.TitleFormatter)
 */
public void setTitleMonths(CharSequence[] monthLabels){
  setTitleFormatter(new MonthArrayTitleFormatter(monthLabels));
}

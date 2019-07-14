public static AlertDialog.Builder createDatePickerDialog(Context context,int minYear,int maxYear,int currentYearDiff,int selectedDay,int selectedMonth,int selectedYear,String title,final boolean checkMinDate,final DatePickerDelegate datePickerDelegate){
  if (context == null) {
    return null;
  }
  LinearLayout linearLayout=new LinearLayout(context);
  linearLayout.setOrientation(LinearLayout.HORIZONTAL);
  linearLayout.setWeightSum(1.0f);
  final NumberPicker monthPicker=new NumberPicker(context);
  final NumberPicker dayPicker=new NumberPicker(context);
  final NumberPicker yearPicker=new NumberPicker(context);
  linearLayout.addView(dayPicker,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,0.3f));
  dayPicker.setOnScrollListener((view,scrollState) -> {
    if (checkMinDate && scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
      checkPickerDate(dayPicker,monthPicker,yearPicker);
    }
  }
);
  monthPicker.setMinValue(0);
  monthPicker.setMaxValue(11);
  linearLayout.addView(monthPicker,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,0.3f));
  monthPicker.setFormatter(value -> {
    Calendar calendar=Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_MONTH,1);
    calendar.set(Calendar.MONTH,value);
    return calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.getDefault());
  }
);
  monthPicker.setOnValueChangedListener((picker,oldVal,newVal) -> updateDayPicker(dayPicker,monthPicker,yearPicker));
  monthPicker.setOnScrollListener((view,scrollState) -> {
    if (checkMinDate && scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
      checkPickerDate(dayPicker,monthPicker,yearPicker);
    }
  }
);
  Calendar calendar=Calendar.getInstance();
  calendar.setTimeInMillis(System.currentTimeMillis());
  final int currentYear=calendar.get(Calendar.YEAR);
  yearPicker.setMinValue(currentYear + minYear);
  yearPicker.setMaxValue(currentYear + maxYear);
  yearPicker.setValue(currentYear + currentYearDiff);
  linearLayout.addView(yearPicker,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,0.4f));
  yearPicker.setOnValueChangedListener((picker,oldVal,newVal) -> updateDayPicker(dayPicker,monthPicker,yearPicker));
  yearPicker.setOnScrollListener((view,scrollState) -> {
    if (checkMinDate && scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
      checkPickerDate(dayPicker,monthPicker,yearPicker);
    }
  }
);
  updateDayPicker(dayPicker,monthPicker,yearPicker);
  if (checkMinDate) {
    checkPickerDate(dayPicker,monthPicker,yearPicker);
  }
  if (selectedDay != -1) {
    dayPicker.setValue(selectedDay);
    monthPicker.setValue(selectedMonth);
    yearPicker.setValue(selectedYear);
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(context);
  builder.setTitle(title);
  builder.setView(linearLayout);
  builder.setPositiveButton(LocaleController.getString("Set",R.string.Set),(dialog,which) -> {
    if (checkMinDate) {
      checkPickerDate(dayPicker,monthPicker,yearPicker);
    }
    datePickerDelegate.didSelectDate(yearPicker.getValue(),monthPicker.getValue(),dayPicker.getValue());
  }
);
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  return builder;
}

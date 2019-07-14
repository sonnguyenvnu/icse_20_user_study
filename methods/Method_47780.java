@NonNull private BaseWidget constructWidget(@NonNull Habit habit){
switch (widgetType) {
case CHECKMARK:
    return new CheckmarkWidget(context,widgetId,habit);
case FREQUENCY:
  return new FrequencyWidget(context,widgetId,habit);
case SCORE:
return new ScoreWidget(context,widgetId,habit);
case HISTORY:
return new HistoryWidget(context,widgetId,habit);
case STREAKS:
return new StreakWidget(context,widgetId,habit);
}
throw new IllegalStateException();
}

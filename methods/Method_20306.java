public CharSequence toString(Context context){
  if (pluralRes > 0) {
    if (formatArgs != null) {
      return context.getResources().getQuantityString(pluralRes,quantity,formatArgs);
    }
 else {
      return context.getResources().getQuantityString(pluralRes,quantity);
    }
  }
 else   if (stringRes > 0) {
    if (formatArgs != null) {
      return context.getResources().getString(stringRes,formatArgs);
    }
 else {
      return context.getResources().getText(stringRes);
    }
  }
 else {
    return string;
  }
}

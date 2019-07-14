private void fillLanguages(){
  final LocaleController.LocaleInfo currentLocale=LocaleController.getInstance().getCurrentLocaleInfo();
  Comparator<LocaleController.LocaleInfo> comparator=(o,o2) -> {
    if (o == currentLocale) {
      return -1;
    }
 else     if (o2 == currentLocale) {
      return 1;
    }
 else     if (o.serverIndex == o2.serverIndex) {
      return o.name.compareTo(o2.name);
    }
    if (o.serverIndex > o2.serverIndex) {
      return 1;
    }
 else     if (o.serverIndex < o2.serverIndex) {
      return -1;
    }
    return 0;
  }
;
  sortedLanguages=new ArrayList<>();
  unofficialLanguages=new ArrayList<>(LocaleController.getInstance().unofficialLanguages);
  ArrayList<LocaleController.LocaleInfo> arrayList=LocaleController.getInstance().languages;
  for (int a=0, size=arrayList.size(); a < size; a++) {
    LocaleController.LocaleInfo info=arrayList.get(a);
    if (info.serverIndex != Integer.MAX_VALUE) {
      sortedLanguages.add(info);
    }
 else {
      unofficialLanguages.add(info);
    }
  }
  Collections.sort(sortedLanguages,comparator);
  Collections.sort(unofficialLanguages,comparator);
}

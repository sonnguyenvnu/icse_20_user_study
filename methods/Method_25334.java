/** 
 * Parse import order string and create appropriate  {@link ImportOrganizer}.
 * @param importOrder the import order, either static-first or static-last.
 * @return the {@link ImportOrganizer}
 */
public static ImportOrganizer getImportOrganizer(String importOrder){
switch (importOrder) {
case "static-first":
    return ImportOrganizer.STATIC_FIRST_ORGANIZER;
case "static-last":
  return ImportOrganizer.STATIC_LAST_ORGANIZER;
case "android-static-first":
return ImportOrganizer.ANDROID_STATIC_FIRST_ORGANIZER;
case "android-static-last":
return ImportOrganizer.ANDROID_STATIC_LAST_ORGANIZER;
default :
throw new IllegalStateException("Unknown import order: '" + importOrder + "'");
}
}

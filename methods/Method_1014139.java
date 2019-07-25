/** 
 * Maps a  {@link HumanLanguageInterpreter} to an {@link HumanLanguageInterpreterDTO}.
 * @param hli the human language interpreter
 * @param locale the locale to use for the DTO
 * @return the corresponding DTO
 */
public static HumanLanguageInterpreterDTO map(HumanLanguageInterpreter hli,Locale locale){
  HumanLanguageInterpreterDTO dto=new HumanLanguageInterpreterDTO();
  dto.id=hli.getId();
  dto.label=hli.getLabel(locale);
  final Set<Locale> supportedLocales=hli.getSupportedLocales();
  if (supportedLocales != null) {
    dto.locales=new HashSet<String>(supportedLocales.size());
    for (    final Locale supportedLocale : supportedLocales) {
      dto.locales.add(supportedLocale.toString());
    }
  }
  return dto;
}

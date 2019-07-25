public TimeArrow translate(UTranslate translate){
  return new TimeArrow(translate.getTranslated(start),translate.getTranslated(end),label,spriteContainer,type);
}

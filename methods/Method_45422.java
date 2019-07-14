private static Function<RestSubResourceSetting,RestSetting> toSubResourceSetting(){
  return new Function<RestSubResourceSetting,RestSetting>(){
    @Override public RestSetting apply(    final RestSubResourceSetting input){
      RestSetting[] settings=input.getSettings();
      return MocoRest.id(asIdMatcher(input.id)).name(input.getName()).settings(head(settings),tail(settings));
    }
  }
;
}

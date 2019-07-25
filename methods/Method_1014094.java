@Modified protected void modified(Map<String,Object> config){
  if (config != null) {
    this.keyword=config.containsKey(CONFIG_KEYWORD) ? config.get(CONFIG_KEYWORD).toString() : DEFAULT_KEYWORD;
    this.listeningItem=config.containsKey(CONFIG_LISTENING_ITEM) ? config.get(CONFIG_LISTENING_ITEM).toString() : null;
    this.defaultTTS=config.containsKey(CONFIG_DEFAULT_TTS) ? config.get(CONFIG_DEFAULT_TTS).toString() : null;
    this.defaultSTT=config.containsKey(CONFIG_DEFAULT_STT) ? config.get(CONFIG_DEFAULT_STT).toString() : null;
    this.defaultKS=config.containsKey(CONFIG_DEFAULT_KS) ? config.get(CONFIG_DEFAULT_KS).toString() : null;
    this.defaultHLI=config.containsKey(CONFIG_DEFAULT_HLI) ? config.get(CONFIG_DEFAULT_HLI).toString() : null;
    this.defaultVoice=config.containsKey(CONFIG_DEFAULT_VOICE) ? config.get(CONFIG_DEFAULT_VOICE).toString() : null;
    for (    String key : config.keySet()) {
      if (key.startsWith(CONFIG_PREFIX_DEFAULT_VOICE)) {
        String tts=key.substring(CONFIG_PREFIX_DEFAULT_VOICE.length());
        defaultVoices.put(tts,config.get(key).toString());
      }
    }
  }
}

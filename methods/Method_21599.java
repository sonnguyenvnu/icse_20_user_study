private void parseAndUpdateScriptType(String scriptType){
  String scriptTypeUpper=scriptType.toUpperCase();
switch (scriptTypeUpper) {
case "INLINE":
    this.scriptType=ScriptType.INLINE;
  break;
case "INDEXED":
case "STORED":
this.scriptType=ScriptType.STORED;
break;
}
}

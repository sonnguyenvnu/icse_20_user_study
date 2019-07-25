void execute(){
  int i;
  for (i=0; i != model.rulesLeft.size(); i++) {
    String rl=model.rulesLeft.get(i);
    int j;
    for (j=0; j != rl.length(); j++) {
      char x=rl.charAt(j);
      if (x == '0' || x == '1') {
        if (pins[j].value == (x == '1'))         continue;
        break;
      }
      if (x == '?')       continue;
      if (x == '+') {
        if (pins[j].value && !lastValues[j])         continue;
        break;
      }
      if (x == '-') {
        if (!pins[j].value && lastValues[j])         continue;
        break;
      }
      if (x >= 'a' && x <= 'z') {
        patternValues[x - 'a']=pins[j].value;
        continue;
      }
      if (x >= 'A' && x <= 'z') {
        if (patternValues[x - 'A'] != pins[j].value)         break;
        continue;
      }
    }
    if (j != rl.length())     continue;
    String rr=model.rulesRight.get(i);
    for (j=0; j != rr.length(); j++) {
      char x=rr.charAt(j);
      highImpedance[j + inputCount]=false;
      if (x >= 'a' && x <= 'z')       pins[j + inputCount].value=patternValues[x - 'a'];
 else       if (x == '_')       highImpedance[j + inputCount]=true;
 else       pins[j + inputCount].value=(x == '1');
    }
    for (j=0; j != postCount; j++)     lastValues[j]=pins[j].value;
    break;
  }
}

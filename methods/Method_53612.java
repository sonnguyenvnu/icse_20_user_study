public String getKeyFrames(){
  String str="\n";
  start_caret=1;
  end_caret=1;
  String target=mTarget.getText();
  for (int i=0; i < values[POS].length; i++) {
    double pos=values[POS][i];
    double per=values[PERIOD][i];
    double amp=values[AMP][i];
    double off=values[OFFSET][i];
    String xmlstr="<KeyCycle \n";
    xmlstr+="        motion:framePosition=\"" + (int)(0.5 + pos * 100) + "\"\n";
    xmlstr+="        motion:target=\"@+id/" + target + "\"\n";
    xmlstr+="        motion:wavePeriod=\"" + (int)(per) + "\"\n";
    xmlstr+="        motion:waveOffset=\"" + CycleView.MainAttribute.process(off,mAttrIndex) + "\"\n";
    xmlstr+="        motion:waveShape=\"" + waveShapeName[mMode.getSelectedIndex()] + "\"\n";
    xmlstr+="        " + CycleView.MainAttribute.Names[mAttrIndex] + "=\"" + CycleView.MainAttribute.process(amp,mAttrIndex) + "\"/>\n\n";
    if (selected == i) {
      start_caret=str.length();
    }
    str+=xmlstr;
    if (selected == i) {
      end_caret=str.length();
    }
  }
  return str;
}

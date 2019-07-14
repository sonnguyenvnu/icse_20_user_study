public void parseXML(String str){
  try {
    InputStream inputStream=new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
    SAXParserFactory factory=SAXParserFactory.newInstance();
    SAXParser saxParser=factory.newSAXParser();
    ParseResults results=new ParseResults();
    saxParser.parse(inputStream,new DefaultHandler(){
      public void startElement(      String uri,      String localName,      String qName,      Attributes attributes) throws SAXException {
        if ("KeyCycle".equals(qName)) {
          for (int i=0; i < attributes.getLength(); i++) {
switch (attributes.getQName(i)) {
case "motion:framePosition":
              results.setFramePosition(Integer.parseInt(attributes.getValue(i)) / 100f);
            break;
case "motion:target":
          results.setTarget(attributes.getValue(i).substring(5));
        break;
case "motion:wavePeriod":
      results.setWavePeriod(Float.parseFloat(attributes.getValue(i)));
    break;
case "motion:waveOffset":
  results.setWaveOffset(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "motion:waveShape":
String shape=attributes.getValue(i);
for (int j=0; j < waveShapeName.length; j++) {
if (waveShapeName[j].equals(shape)) {
results.setShape(j);
}
}
break;
case "motion:transitionPathRotate":
results.setValueType(CycleView.Prop.PATH_ROTATE);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:alpha":
results.setValueType(CycleView.Prop.ALPHA);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:elevation":
results.setValueType(CycleView.Prop.ELEVATION);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:rotation":
results.setValueType(CycleView.Prop.ROTATION);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:rotationX":
results.setValueType(CycleView.Prop.ROTATION_X);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:rotationY":
results.setValueType(CycleView.Prop.ROTATION_Y);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:scaleX":
results.setValueType(CycleView.Prop.SCALE_X);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:scaleY":
results.setValueType(CycleView.Prop.SCALE_Y);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:translationX":
results.setValueType(CycleView.Prop.TRANSLATION_X);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:translationY":
results.setValueType(CycleView.Prop.TRANSLATION_Y);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "android:translationZ":
results.setValueType(CycleView.Prop.TRANSLATION_Z);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
case "motion:progress":
results.setValueType(CycleView.Prop.PROGRESS);
results.setWaveValue(Float.parseFloat(trimDp(attributes.getValue(i))));
break;
}
}
if (results.tmpValueType.ordinal() == mAttrIndex) {
results.add();
}
}
}
public void endElement(String uri,String localName,String qName) throws SAXException {
}
}
);
values=results.values;
selected=values[POS].length / 2;
mMode.setSelectedIndex(results.shape);
mTarget.setText(results.target);
mAttrIndex=results.valueType.ordinal();
update();
}
 catch (ParserConfigurationException e) {
e.printStackTrace();
}
catch (Exception e) {
e.printStackTrace();
}
}

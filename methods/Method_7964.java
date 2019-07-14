private void updateWaveform(){
  if (currentMessageObject == null || documentAttachType != DOCUMENT_ATTACH_TYPE_AUDIO) {
    return;
  }
  for (int a=0; a < documentAttach.attributes.size(); a++) {
    TLRPC.DocumentAttribute attribute=documentAttach.attributes.get(a);
    if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
      if (attribute.waveform == null || attribute.waveform.length == 0) {
        MediaController.getInstance().generateWaveform(currentMessageObject);
      }
      useSeekBarWaweform=attribute.waveform != null;
      seekBarWaveform.setWaveform(attribute.waveform);
      break;
    }
  }
}

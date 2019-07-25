/** 
 * Adds a ContentItem as Preset, with presetID. Note that a eventually existing id in preset will be overwritten by presetID
 * @param presetID
 * @param preset
 * @throws ContentItemNotPresetableException if ContentItem is not presetable
 */
public void put(int presetID,ContentItem preset) throws ContentItemNotPresetableException {
  preset.setPresetID(presetID);
  if (preset.isPresetable()) {
    mapOfPresets.put(presetID,preset);
    writeToStorage();
  }
 else {
    throw new ContentItemNotPresetableException();
  }
}

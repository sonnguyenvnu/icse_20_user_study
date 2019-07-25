public Device build(Device prototype,UDAVersion deviceVersion,URL baseURL) throws ValidationException {
  List<Device> embeddedDevicesList=new ArrayList<>();
  for (  MutableDevice embeddedDevice : embeddedDevices) {
    embeddedDevicesList.add(embeddedDevice.build(prototype,deviceVersion,baseURL));
  }
  return prototype.newInstance(udn,deviceVersion,createDeviceType(),createDeviceDetails(baseURL),createIcons(),createServices(prototype),embeddedDevicesList);
}

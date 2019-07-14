/** 
 * Add a directory that should be searched for font data. <br/> On Mac OS X, the following directories are added by default: <UL> <LI>/System/Library/Fonts <LI>/Library/Fonts <LI>~/Library/Fonts </UL> On Windows, all drive letters are searched for WINDOWS\Fonts or WINNT\Fonts, any that exists is added. <br/><br/> On Linux or any other platform, you'll need to add the directories by hand. (If there are actual standards here that we can use as a starting point, please file a bug to make a note of it)
 */
public void addFonts(String directory){
  mapper.insertDirectory(directory);
}

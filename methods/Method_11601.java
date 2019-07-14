/** 
 * create a spider with pageProcessor.
 * @param pageProcessor pageProcessor
 * @return new spider
 * @see PageProcessor
 */
public static Spider create(PageProcessor pageProcessor){
  return new Spider(pageProcessor);
}

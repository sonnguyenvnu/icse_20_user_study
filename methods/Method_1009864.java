/** 
 * Sets the "Get Started" button with a payload "hi". It also set the "Greeting Text" which the user sees when it opens up the chat window. Uncomment the  {@code @PostConstruct} annotation only after you have verified your webhook.
 */
public void init(){
  setGetStartedButton("hi");
  setGreetingText(new Payload[]{new Payload().setLocale("default").setText("JBot is a Java Framework to help" + " developers make Facebook, Slack and Twitter bots easily. You can see a quick demo by clicking " + "the \"Get Started\" button.")});
}

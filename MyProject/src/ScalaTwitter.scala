import twitter4j.TwitterFactory
import twitter4j.Twitter
import twitter4j.conf.ConfigurationBuilder
import twitter4j._
 
object ScalaTwitterClientExample {
   
  def main(args : Array[String]) {
     
    // (1) config work to create a twitter object
    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("FoaIzZ9V1mevww8Wu7guw42tT")
      .setOAuthConsumerSecret("xBhkBTfYuQggQ9GJUOFxr4t3kCvto4PNyJCaTgVM61vjjQQc6H")
      .setOAuthAccessToken("2927765180-kbObXZ0QMIFTK9sGiSCRdJRxc6tjm9eGNIRHkBy")
      .setOAuthAccessTokenSecret("CogOnyHuWE2iNfgM3MN0g4U2IJrbTB9z8LkRc0pHMXixo")
    val tf = new TwitterFactory(cb.build())
    val twitter = tf.getInstance()
 
    // (2) use the twitter object to get your friend's timeline
     twitter.updateStatus(new StatusUpdate("testing"))
    //println(statuses)
    /*System.out.println("Showing friends timeline.")
    val it = statuses.iterator()
    while (it.hasNext()) {
      val status = it.next()
      println(status.getUser().getName() + ":" +
              status.getText());*/
    
 
  }
}

import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random



object SplitPane extends SimpleSwingApplication {
  
  def top= new MainFrame{
    
     val textArea1 = new TextArea {
      text = "input areahihidfbdsfdsgsdgsdgsddssdsdfsf\nline two"
    
    }
    val textArea2 = new TextArea {
      text = "output area\nline two"
    
    }
    
     val textArea3 = new TextArea {
      text = "output area\nline two"
    
    }
     
    val split:SplitPane = new SplitPane(Orientation.Vertical, 
  new BoxPanel(Orientation.Vertical) {  //This is the 1st UI element in the split
    for(i <- 1 to 20)
      contents += Button(i.toString)({println("SplitLeft:Button:"+i)})
  },
  new ScrollPane() {  //This is the 2nd UI element in the split; it's a ScrollPane that contains...
    contents = new BoxPanel(Orientation.Vertical) {  //Another BoxPanel with buttons
      for(i <- 1 to 20)
        contents += Button(i.toString)({println("SplitRight:Scroll:Button"+i)})
    }  
  })
    
    contents=split
    
  }

}
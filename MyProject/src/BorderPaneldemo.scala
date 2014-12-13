import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random

object BorderPaneldemo extends SimpleSwingApplication{
  
  
  def top= new MainFrame{
    title="Split Pane"
      val checkBox = new CheckBox { text = "Check me" }
    val label = new Label {
      text = "I'm a big label!."
      font = new Font("Ariel", java.awt.Font.ITALIC, 24)
    }
    preferredSize = new Dimension(1250, 750)
    val textArea = new TextArea {
      text = "initial text\nline two"
    
    }
    
   val input= new ScrollPane() {  //This is the 2nd UI element in the split; it's a ScrollPane that contains...
     preferredSize = new Dimension(120, 750)
     contents = new BoxPanel(Orientation.Vertical) {  //Another BoxPanel with buttons
      for(i <- 1 to 20)
        contents += Button(i.toString)({println("SplitRight:Scroll:Button"+i)})
    }  
  }
   
   val output= new ScrollPane() {  //This is the 2nd UI element in the split; it's a ScrollPane that contains...
    preferredSize = new Dimension(120, 120)
     contents = new BoxPanel(Orientation.Horizontal) {  //Another BoxPanel with buttons
      for(i <- 1 to 20)
        contents += Button(i.toString)({println("SplitRight:Scroll:Button"+i)})
    }  
  }
   
   val split:SplitPane = new SplitPane(Orientation.Horizontal, 
  label,textArea)
    
   
    val gridPanel = new GridPanel(1, 2) {
      contents += checkBox
      contents += label
      contents += textArea
    }
     val gridPanel2 = new GridPanel(1, 2) {
      contents += checkBox
      contents += label
      contents += textArea
    }
      val gridPanel3 = new GridPanel(1, 2) {
      contents += checkBox
      contents += label
      contents += textArea
    }
    contents = new BorderPanel {
      layout(input) = East	
      layout(output) =South 
      layout(label) = Center
      
    }
      
      
    
    
  }

}
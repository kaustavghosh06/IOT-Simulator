
import java.awt.Color
import scala.swing._
import scala.swing.event._

class AI extends MainFrame {
   def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }

  val la = new Label("Input Window")
  val nameField = new TextField { title="1"
    columns = 2 }
  val nameField1 = new TextField { columns = 2 }
  val commentField = new TextArea { rows = 8; lineWrap = true; wordWrap = true }

  la.foreground = Color.BLUE
  title = "My Scala Project"
  preferredSize = new Dimension(500, 300)

  restrictHeight(nameField)
  restrictHeight(nameField1)

  contents = new BoxPanel(Orientation.Vertical) {
    contents += la

    //contents += new BoxPanel(Orientation.Horizontal) {
    contents += Button("Sensor 1") { pressMe() }
    contents += Swing.VStrut(2)
    contents += nameField
    contents += Swing.VStrut(5)

     //contents += new BoxPanel(Orientation.Horizontal) {
    contents += Button("Sensor 2") { pressMe() }
    contents += Swing.VStrut(2)
    contents += nameField1
      
    contents += Swing.VStrut(10)
    contents += new Label("Comments")
    contents += Swing.VStrut(3)
    contents += new ScrollPane(commentField)
    contents += Swing.VStrut(5)
    
    contents += Swing.VStrut(5)
    contents += new Button("Start")
    contents += Swing.HGlue
    contents += new Button("Pause")
    contents += Button("Close"){ reportAndClose() }
    border = Swing.EmptyBorder(10, 10, 10, 10)
    
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
  listenTo(nameField)
  listenTo(nameField)
  listenTo(commentField)
 

  reactions += {
   case EditDone(`nameField`) => 
      println("Your Sensor value is now: " + nameField.text)
   case EditDone(`nameField1`) => 
      println("Your Sensor value is now: " + nameField1.text)
   case EditDone(`commentField`) => 
      println("You changed the comments")
  }

    
    
    
    
 
  

  def pressMe() {
    Dialog.showMessage(contents.head, "Thank you!", title="You selected a sensor")
  }

  /*def changeText() {
    val r = Dialog.showInput(contents.head, "New label text", initial=la.text)
    r match {
      case Some(s) => la.text = s
      case None => 
    }
  }*/

  def closeMe() {
    val res = Dialog.showConfirmation(contents.head, 
				      "Do you really want to quit?", 
				      optionType=Dialog.Options.YesNo,
				      title=title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
  }
 
  def reportAndClose() {
    println("Your value1: " + nameField.text)
    println("Your value2: " + nameField1.text)
    println("Comments: " + commentField.text)
    val res = Dialog.showConfirmation(contents.head, 
				      "Do you really want to quit?", 
				      optionType=Dialog.Options.YesNo,
				      title=title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
  
   
    
  }
}

object GuiProgramFour {
  def main(args: Array[String]) {
    val ai = new AI
    ai.visible = true
  }
}
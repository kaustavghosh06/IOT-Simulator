import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.swing.event._
import javax.swing.{JFrame,JLayeredPane,JLabel,JPanel}
import java.awt.event.{MouseMotionAdapter, MouseEvent, MouseAdapter}
import java.io._
import twitter4j.TwitterFactory
import twitter4j.Twitter
import twitter4j.conf.ConfigurationBuilder
import twitter4j._


object Main extends SimpleSwingApplication {
  def top = new MainFrame {
    
    var out=""
	var m=""
	var n=""
	var m1=""
	var n1=""
	var p="0"
	var z="0"
	var temperature:Map[Int,String]=Map()
	var light:Map[Int,String]=Map()
	var buzzer:Map[Int,String]=Map()
	var sound:Map[Int,String]=Map()
	var twitter:Map[Int,String]=Map()
	
	
	
	
	val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("FoaIzZ9V1mevww8Wu7guw42tT")
      .setOAuthConsumerSecret("xBhkBTfYuQggQ9GJUOFxr4t3kCvto4PNyJCaTgVM61vjjQQc6H")
      .setOAuthAccessToken("2927765180-kbObXZ0QMIFTK9sGiSCRdJRxc6tjm9eGNIRHkBy")
      .setOAuthAccessTokenSecret("CogOnyHuWE2iNfgM3MN0g4U2IJrbTB9z8LkRc0pHMXixo")
    val tf = new TwitterFactory(cb.build())
    val twitter1 = tf.getInstance()
	
	
	
	var sensors=List("Temperature Sensor", "Light Sensor", "Buzzer","Sound Sensor","Twitter")
	var colors=List("Red","Blue","Green","Yellow","Orange")
	
	var connections:Map[String,String]=Map()
	var from: ListBuffer[String] = ListBuffer[String]()
	var to: ListBuffer[String] = ListBuffer[String]()
	var count1=0
	var count2=0
	var count3=0
	var count4=0
	var count5=0;
	
   //var s1=("","Temperature Sensor",20)
   //var s2=("","Light Sensor",20)
   //var s3=("","Buzzer",20)
   //var s4=("","Sound Sensor",20)
   var sensorList: ListBuffer[String] = ListBuffer[String]()
   var sensorList1:scala.collection.mutable.Map[String,String]=scala.collection.mutable.Map()
   var sensorList2:Map[String,String]=Map()
   var threshold:scala.collection.mutable.Map[String,String]=scala.collection.mutable.Map()
   
   
   
   //fw.write("hi")
   //fw.close()
    
   var v=new Position(0,0)
   
   var positions:Map[String,List[Int]]=Map()
   
   
    val file_IO = new FileChooser(new java.io.File(".")) {
    	fileSelectionMode = FileChooser.SelectionMode.FilesOnly
    	
    
}
    val editor = new TextArea {
    	font = new java.awt.Font("UM Typewriter",
    			java.awt.Font.PLAIN, 12)
    	columns = 40
    	rows = 20
    	editable = true
    	text = ""
}
    preferredSize = new Dimension(1250, 750)
 
    
    
    //Input Screen Code
    
    
    
    
  val la = new Label("Input Window")
  val nameField = new TextField { title="Internet Of Things GUI"
    columns = 2 }
  val nameField1 = new TextField { columns = 2 }
  val commentField = new TextArea { rows = 8; lineWrap = true; wordWrap = true }

  
  
  def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }
  restrictHeight(nameField)
  restrictHeight(nameField1)
  
  
  val input2= new ScrollPane(){
    
    preferredSize = new Dimension(220, 750)
	contents = new GridPanel(4, 0) {
    contents += new Label{
          text="Input Window" 
            font=new Font("ariel", java.awt.Font.BOLD , 15)
          foreground=Color.RED 
        }
    /*
   contents += new BoxPanel(Orientation.Vertical) {
    contents += Button("Sensor1") { pressMe() }
    contents += Swing.VStrut(2)
    contents += nameField
    contents += Swing.VStrut(5)

    //contents = new BoxPanel(Orientation.Vertical) {
    contents += Button("Sensor 2") { pressMe() }
    contents += Swing.VStrut(2)
    contents += nameField1
    }  
    contents += new BoxPanel(Orientation.Vertical) {
    contents += Swing.VStrut(10)
    contents += new Label("Comments")
    contents += Swing.VStrut(3)
    contents += new ScrollPane(commentField)
    contents += Swing.VStrut(5)
    }
    contents += new BoxPanel(Orientation.Vertical) {
    contents += Swing.VStrut(5)
    contents += new Button("Start")
    contents += Swing.HGlue
    contents += new Button("Pause")
    contents += Button("Close"){ reportAndClose() }
    border = Swing.EmptyBorder(10, 10, 10, 10)
    }
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)*/
  }
  }
  /*
  def pressMe() {
    Dialog.showMessage(contents.head, "Thank you!", title="You selected a sensor")
  }*/

    
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
  
  
   def pressMe(i:String) {
    Dialog.showMessage(contents.head, "Do You Want to delete "+ i, title="You selected a sensor")
    sensorList -=i
    sensorList1 -=i
    sensorList2 -=i
    connections -=i
    
    change()
  }
  
  
  
  
  def perform(){
   for((x,y)<-connections)//check the connection
   { 
     val i=x.substring(0,3)
     println(i)
     val j=y.substring(0,3)
     println(j)
     if(i=="Tem" && j=="Buz")
     {
    	 val p=sensorList1(x)
    	 val q=sensorList1(y)
    	 //val e=threshold()
    	 println("hi the value is " + sensorList1(x))
    	 println("hi the value is " + sensorList1(y))
    	 
    	 if(p.toInt>threshold(x).toInt){
    	   
    	   sensorList1(y)="1"
    	     out+= y + " has been updated to value " + sensorList1(y) + "\n" 
    	     
    	     //println("Yo" + sensorList1(y))
    	   
    	 }
    	 else{
    	   
    	   sensorList1(y)="0"
    	     out+= y + " has been updated to value " + sensorList1(y) + "\n" 
    	   
    	   
    	   
    	 }
    	 
     }
     
     if(i=="Lig" && j=="Twi")
     {
       
       val p=sensorList1(x)
       val q=sensorList1(y)
       if(p.toInt>25){
         
         twitter1.updateStatus(new StatusUpdate(q))
         out+="Your Tweet " + q + "has been tweeted :)"
         //change()
         
       }
       
     }
     
   }
   change()
   
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
    
  
  


    

    val output= new ScrollPane() {  //This is the 2nd UI element in the split; it's a ScrollPane that contains...

    preferredSize = new Dimension(120, 120)

     contents = new BoxPanel(Orientation.Horizontal) {  //Another BoxPanel with buttons

     contents +=new TextField {
    text = out
    out=""
    contents += new Label{
          text="Output Window" 
            font=new Font("ariel", java.awt.Font.BOLD , 15)
          foreground=Color.RED 
        }  
    font = new Font("ariel", java.awt.Font.BOLD, 15)
    }


    }  

  }

     val label = new Label {

      text = "Please Create Your Network By Adding Nodes."

      font = new Font("Ariel", java.awt.Font.ITALIC, 24)

    }
  

    contents = new BorderPanel{

      layout(input2) = East

      layout(output) =South 

      layout(label) = Center

      

    }
    
    
    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("New") {
          check_on_exit(1)
        })
        contents += new MenuItem(Action("Save") {
          var currentFile = file_IO.selectedFile
          if ( currentFile == null) {
        	  if (file_IO.showSaveDialog(menuBar) ==
        			  FileChooser.Result.Approve) {
        		  currentFile = file_IO.selectedFile
        				  var out =
        				  new java.io.OutputStreamWriter(
        						  new java.io.BufferedOutputStream(
        								  new java.io.FileOutputStream(currentFile)))
        		  val backup = editor.text
        		  out.write(backup); out.flush(); out.close()
        	  }
          }
          else {
        	  var out =
        			  new java.io.OutputStreamWriter(
        					  new java.io.BufferedOutputStream(
        							  new java.io.FileOutputStream(currentFile)))
        	  val backup = editor.text
        	  out.write(backup); out.flush(); out.close()
          }
        })
        
        
        contents += new MenuItem(Action("Open") {
          import io._
          if (file_IO.showOpenDialog(menuBar) ==
          FileChooser.Result.Approve) {
        	  var currentFile = file_IO.selectedFile
        			  editor.text = new String
        			  for (line <- Source.fromFile(currentFile).getLines)
        				  editor.append(line)
        				  var backup = editor.text
}
          
        })
        contents += new MenuItem(Action("Exit") {
          sys.exit(0)
        })
      }
      contents += new Menu("Nodes"){
        contents += new MenuItem(Action("Add node") {
          funct()
        })}
      contents += new Menu("Connections"){
        contents += new MenuItem(Action("Add Connections") {
          funct1()
        })}
      
     
      
      import Dialog._
      def funct1()
  {
    val s = showInput(contents.head,
                      "FROM",
                      "",
                      Message.Plain, 
                      Swing.EmptyIcon,
                      sensorList, "Temperature Sensor")
                      
    val s11=showInput(contents.head,
    				"TO","",Message.Plain,Swing.EmptyIcon,sensorList,"Red")
    				
    				
    s match {
    case Some(x2) =>
      
      m1=x2
    case None =>
    	println("")
    }
    
     s11 match {
      case Some(x3)=>
      	
      	n1=x3
      	if(n1==m1)
      	{
      	  var s = showConfirmation(contents.head,
      			  "The sensor cannot be connected to itself!",
      			  "Warning",
      			  Options.Default,
      			  Message.Question,
      			  null)
      			  if ( s == Result.Ok )
      				  funct1()
      	}
      	to+=(n1)
       case None =>
        println("")
    }
     println(m1+" and "+n1+" are connected\n"+from+" \n"+to)    
     connections+=(m1->n1)
     print(connections)
     change()
    
  }
      
      
      
    
      import Dialog._
      def funct()
  {
    val possibilities = sensors
    val s = showInput(contents.head,
                      "Select the type of Sensor",
                      "Add Node",
                      Message.Plain, 
                      Swing.EmptyIcon,
                      possibilities, "Temperature Sensor")
    val possibilities1=colors
    val s11=showInput(contents.head,
    				"Select the color for the sensor","Colors",Message.Plain,Swing.EmptyIcon,possibilities1,"Red")
    				
    val s22 = showInput(contents.head,
                      "Enter the value for the sensor",
                      "Value",
                      Message.Plain, 
                      Swing.EmptyIcon,
                      Nil, "20")
                      
        val s23 = showInput(contents.head,
                      "Enter the threshold value for the sensor",
                      "Value",
                      Message.Plain, 
                      Swing.EmptyIcon,
                      Nil, "0")                  
    				
    s match {
    case Some(x) =>
      
      m=x
    case None =>
    	println("")
    }
    
    s11 match {
      case Some(x1)=>
      	
      	n=x1
       case None =>
        println("")
    }
    
    s22 match {
      case Some(x2)=>
      	
      	p=x2
       case None =>
        println("")
    }
     s23 match {
      case Some(x2)=>
      	
      	z=x2
       case None =>
        println("")
    }
    
   if(m=="Temperature Sensor")
      {
      //s1=(s1._1+n,"Temperature Sensor",s1._3)
      count1=count1+1
      temperature+=(count1->n)
      sensorList += (m + count1)
      sensorList1+=(m+count1->p)
      sensorList2+=(m+count1->n)
      threshold+=(m+count1->z)
      
      }
   else if(m=="Twitter")
      {
      //s1=(s1._1+n,"Temperature Sensor",s1._3)
      count5=count5+1
      twitter+=(count5->n)
      sensorList += (m + count5)
      sensorList1+=(m+count5->p)
      sensorList2+=(m+count5->n)
      threshold+=(m+count5->z)
      
      }
      
    else if(m=="Light Sensor")
        	{//s2=(s2._1+n,"Light Sensor",s2._3)
        	count2=count2+1
        	light+=(count2->n)
        	sensorList += (m + count2)
        	sensorList1+=(m+count2->p)
        	sensorList2+=(m+count2->n)
        	threshold+=(m+count2->z)
        	}
         else if(m=="Buzzer")
        	 	{//s3=(s3._1+n,"Buzzer",s3._3)
        	 	count3=count3+1
        	 	buzzer+=(count3->n)
        	 	sensorList += (m + count3)
        	 	sensorList1+=(m+count3->p)
        	 	sensorList2+=(m+count3->n)
        	 	threshold+=(m+count3->z)
        	 	}
        	 	else
        	 	  {//s4=(s4._1+n,"Sound Sensor",s4._3)
        	 	  count4=count4+1
        	 	  sound+=(count4->n)
        	 	
        	 	  sensorList += (m + count4)
        	 	  sensorList1+=(m+count4->p)
        	 	  sensorList2+=(m+count4->n)
        	 	  threshold+=(m+count4->z)
        	 	  
           	 	  }
           
                      
    //println(s1);
    println("Temperature Sensors - "+temperature)
    println("Light Sensors - "+light)
    println("Buzzers - "+buzzer)
    println("Sound Sensors - "+sound)
    println("Twitter Nodes - "+twitter)
    println(sensorList)
    println(sensorList1)
    println(sensorList2)
    println(threshold)
    change()
    
    //println(from)
    //println(to)
   //connections+=(m1->n1)
    //println(connections)
    
    //println(s2)
    //println(s3)
    //println(s4)
    
   
    println()
  }

}
    
def change()
{
  
  //Input Section
  
  val input2 = new ScrollPane(){
    
    preferredSize = new Dimension(450, 750)
    
   contents =new BoxPanel(Orientation.Vertical) {
        contents += new Label{
          text="Input Window" 
            font=new Font("ariel", java.awt.Font.BOLD , 15)
          foreground=Color.RED 
        }
 
     
      //Available Nodes section
    
   contents +=new BoxPanel(Orientation.Vertical){
     
      border = Swing.EmptyBorder(15, 15, 15, 15)
   
   contents +=new Label {  
    text = "Available Nodes"
    font = new Font("ariel", java.awt.Font.BOLD, 15)
  }
   
  
  for(i<-sensorList){
    
    val flow=new FlowPanel(){
   
      val circ=new Panel{
    
    preferredSize = new Dimension(20,20)
    
    name=i
    //border = Swing.EmptyBorder(15, 15, 15, 15)
    opaque = false
    
    
    override def paintComponent(g:Graphics2D) {
    	val g2 = g.asInstanceOf[java.awt.Graphics2D]
    			//var a="red"
//g2.setColor(java.awt.Color.gray)
//g2.fill(new java.awt.Rectangle(350,250))
    	
    	if(sensorList2(i)=="Red")
    	{
    	g2.setColor(java.awt.Color.red)
    	}
    	if(sensorList2(i)=="Blue")
    	{
    	g2.setColor(java.awt.Color.blue)
    	}
    	if(sensorList2(i)=="Green")
    	{
    	  g2.setColor(java.awt.Color.green)
    	}
    	
    	if(sensorList2(i)=="Orange")
    	{
    	  g2.setColor(java.awt.Color.orange)
    	}
    	if(sensorList2(i)=="Yellow")
    	{
    	  g2.setColor(java.awt.Color.yellow)
    	}
    	
    	
//if ( mouseclicked ) {
    	g2.fillOval(0,0, 15, 15)
    	
	//g2.fillOval(mouseX+20,mouseY+20, 25, 25)
	

	
  //g2.drawString("hi",mouseX,mouseY)
	//g2.drawImage(img, mouseX, mouseY, null)
    	//mouseclicked = false
//}
    			}
      }
      
      contents+=circ
      contents += Button(i){ pressMe(i) }
      
    }
    var but=new Button("Update")
    var thresh=new TextField{
      name=i
      text=threshold(i)
      columns=2
    }
    var namefield3= new TextField { name=i
      text= sensorList1(i)
    columns = 2 }
    val flow2=new FlowPanel(){
      
    
       contents += namefield3
       //restrictHeight(namefield3)
       but.name=i
       contents+=but
       contents+=thresh
       
      
    }
   // contents += Swing.VStrut(2)
   
    //restrictHeight(namefield3)
   // restrictHeight(flow2)
    contents += flow2
    //contents += Swing.VStrut(5)
   // restrictHeight(flow)
    contents +=flow
    listenTo(but)
    
    reactions += {
      
      case ButtonClicked(but)=>
        if(but.name ==i && namefield3.name ==i && thresh.name==i )
        {
        sensorList1(i)=namefield3.text.toString()
        threshold(i)=thresh.text.toString()
        println("I am the new thresh" + threshold(i))
        }
       // println(namefield3.text.toString())
        println(sensorList1)
       
        
 
        
      
    }
    
    
    def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }
    
    
    
  }
  }
   
  
   //Connections section     
   contents +=new BoxPanel(Orientation.Vertical)
   {
     preferredSize = new Dimension(100,250)
     contents +=new Label {
    text = "Connections"
    font = new Font("ariel", java.awt.Font.BOLD, 15)
     }
    for((n,m)<-connections){
      
      contents +=new Label {
    text = n + " is connected to " + m
    font = new Font("ariel", java.awt.Font.BOLD, 13)
     }      
    }
     
   }
   
    contents += Swing.VStrut(100)
    
    
    //Simulator Section
    
    
    contents +=new BoxPanel(Orientation.Vertical)
   {
     
    contents +=new Label {
    text = "Simulator"
    font = new Font("ariel", java.awt.Font.BOLD, 15)
    }
    var st= new Button("Simulate")
    contents+=st
    //contents+=new Button("Stop")
    //contents+=new Button("Pause")
    
    
     listenTo(st)
    
    reactions += {
      
      case ButtonClicked(b)=>
        perform()
  
     
   }
  
       
        
 
        
      
    }
    
    }  
    
        
  }
  
  
  
  
   
  //Center Screen 
   
  val screen=new ScrollPane(){
    
  var CanvasList:ListBuffer[Panel]=ListBuffer[Panel]()
   var mouseX = 0; var mouseY = 0
   var nmouseX = 0; var nmouseY = 0
   var mouseclicked = false
   
   for(i<-sensorList)
   {
  CanvasList+=new Panel{
    
    preferredSize = new Dimension(200,200)
    
    name=i
    border = Swing.EmptyBorder(15, 15, 15, 15)
    opaque = false
    
    
    override def paintComponent(g:Graphics2D) {
    	val g2 = g.asInstanceOf[java.awt.Graphics2D]
    	if(sensorList2(i)=="Red")
    	{
    	g2.setColor(java.awt.Color.red)
    	}
    	if(sensorList2(i)=="Blue")
    	{
    	g2.setColor(java.awt.Color.blue)
    	}
    	
    	if(sensorList2(i)=="Green")
    	{
    	  g2.setColor(java.awt.Color.green)
    	}
    	  if(sensorList2(i)=="Orange")
    	{
    	  g2.setColor(java.awt.Color.orange)
    	}
    	if(sensorList2(i)=="Yellow")
    	{
    	  g2.setColor(java.awt.Color.yellow)
    	}

    	positions+=(i->List(mouseX,mouseY))
    	println(positions)
    	val fw = new FileWriter("location.txt", true) 
    	fw.write(i + "was moved to" + " cordinates (" + positions(i)(0) +"," + positions(i)(1) + ")" )
    	fw.write("\n")
    	fw.close()
    	g2.fillOval(mouseX, mouseY, 25, 25)
    	
	//g2.fillOval(mouseX+20,mouseY+20, 25, 25)
	

	
  //g2.drawString("hi",mouseX,mouseY)
	//g2.drawImage(img, mouseX, mouseY, null)
    	mouseclicked = false
//}
    			}
    
listenTo(mouse.clicks,mouse.moves)
reactions += {
case MouseClicked(_, p, _, 1, _) => {
mouseX = p.x
mouseY = p.y
mouseclicked = true
//repaint
			}
case MouseDragged(_,p,_) =>{
  
  mouseX = p.x
  mouseY = p.y
  
  
  repaint
    
		}
    }
    
    
 }
  
  
 }
  
  
  contents = new FlowPanel{
  
		var count3=0
	  for(i<-CanvasList)
	  {
	    contents+=CanvasList(count3)
	    count3=count3+1
	  }

      

    }
  
  }
  
  
  //Output Screen
  
  val output= new ScrollPane() {  //This is the 2nd UI element in the split; it's a ScrollPane that contains...

    preferredSize = new Dimension(120, 120)

     contents = new BoxPanel(Orientation.Horizontal) { //Another BoxPanel with buttons
              contents += new Label{
          text="Output Window" 
            font=new Font("ariel", java.awt.Font.BOLD , 15)
          foreground=Color.RED 
        }
 
       contents +=new TextField {
    text = out
    out=""
    font = new Font("ariel", java.awt.Font.BOLD, 15)
    }

    	

    }  

  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  contents = new BorderPanel{

      layout(input2) = East

      layout(output) =South 

      layout(screen) = Center

      

    }
  
  }


      
         def check_on_exit(oper : Int) = {
        var backup = editor.text
    	  if ( backup != editor.text ) {
    		  import Dialog._
    		  var s = showConfirmation(menuBar,
    				  "File is not saved,\n would you like to save it?",
    						  "Save File", Options.YesNoCancel,
    						  Message.Question, null)
    						  if ( s == Result.Yes ) {
    							  if (file_IO.showSaveDialog(menuBar) ==
    									  FileChooser.Result.Approve) {
    								  var currentFile = file_IO.selectedFile
    										  var out =
    										  new java.io.OutputStreamWriter(
    										      new java.io.BufferedOutputStream(
    										    		  new java.io.FileOutputStream(currentFile)))
    								  backup = editor.text
    								  out.write(backup); out.flush(); out.close()
    							  }
    							  cleanUp(1) // remember: oper is the only
    						  } // argument of this method
    						  else if ( s == Result.No )
    							  cleanUp(0)
    	  }
      }
      
      def cleanUp(oper:Int) = {
    	  var currentFile = null
    			  if (oper == 0 )
    			  {}
    				  else {
    					  editor.text = ""
    							 val backup = ""
    				  }
      }
     
  }
     
    
}

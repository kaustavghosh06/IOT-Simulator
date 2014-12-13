import javax.swing.{JFrame,JLayeredPane,JLabel,JPanel}
import java.awt.{Color, Dimension}

import scala.swing._
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants
import java.awt.event.{MouseMotionAdapter, MouseEvent, MouseAdapter}

import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import java.awt.image._
import javax.imageio.ImageIO._

	
	object JLayeredPaneTest extends SimpleSwingApplication
	{
	var x=10.0;
	def top = new MainFrame { // top is a required method
    title = "A Sample Scala Swing GUI"
	
	  val frame = new JFrame("Scales")
	  frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE)
	 
	  val layeredPane: JLayeredPane = new JLayeredPane()
	  layeredPane.setPreferredSize(new java.awt.Dimension(1000, 1000))
	  frame.getContentPane.add(layeredPane)
	  frame.pack
	 
	  	
	  layeredPane.setLayout(new java.awt.GridLayout(4,4))
	
	  val panel = new GraphicPanel()
	  panel.setPreferredSize(frame.getContentPane.getSize)
	  panel.setBounds(1,0,200,200)
	
	  val panel2 = new GraphicPanel()
	  panel2.setPreferredSize(frame.getContentPane.getSize)
	  panel2.setBounds(new java.awt.Rectangle(1,0,200,200))
	 
	                         
	  layeredPane.add(panel,new java.lang.Integer(2), 1)
	  layeredPane.add(panel2,new java.lang.Integer(2), 2)
	  
	 
	  
	  layeredPane.setLayer(panel2, 2)
	
	  frame.setVisible(true)
	 
	  panel.graphic = Fill(HSV(0,0,0),Circle(100.0,100.0,x))
	  panel2.graphic = Fill(HSV(1,1,1),Circle(100.0,100.0,20.0)) 
	 
	  panel.setBackground(new Color(0, 0,0,0))
	  panel2.setBackground(new Color(200, 100, 100,0))
	  
	 panel.addMouseMotionListener(new MouseMotionAdapter {
        override def mouseDragged(e: MouseEvent): Unit = {
          //x=20
          panel.graphic = Fill(HSV(1,1,1),Circle(e.getX(),e.getY(),x))
          panel.repaint()
        }
      })

      panel2.addMouseMotionListener(new MouseMotionAdapter {
        override def mouseDragged(e: MouseEvent): Unit = {
          //x=20
          panel2.graphic = Fill(HSV(1,1,1),Circle(e.getX(),e.getY(),x))
          panel2.repaint()
        }
      })
	  
	  
	  
	  panel.repaint()
	  panel2.repaint()
	}
	}
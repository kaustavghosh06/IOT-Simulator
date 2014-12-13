import javax.swing
	
	import javax.swing.JPanel
	import java.awt.{Graphics, Graphics2D}
	
	class GraphicPanel extends JPanel {
	  var graphic : Graphic = Over()
	 
	  override def paint(graphics : Graphics) {
	    graphics.setColor(java.awt.Color.WHITE)
	    graphics.fillRect(0, 0, getWidth, getHeight)
	    val gc = new GraphicsContext(graphics.asInstanceOf[Graphics2D])
	    graphic.render(gc)
	  }
	}
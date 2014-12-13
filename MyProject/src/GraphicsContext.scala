import java.awt.Graphics2D
import java.awt.Paint
	
	class GraphicsContext(val g2d : Graphics2D) {
	  var outlinePaint : Paint = Color.BLACK
	  var fillPaint : Paint = Color.CLEAR
	 
	  def drawShape(shape : java.awt.Shape) {
	    g2d.setPaint(fillPaint)
	    g2d.fill(shape)
	    g2d.setPaint(outlinePaint)
	    g2d.draw(shape)
	  }
	}
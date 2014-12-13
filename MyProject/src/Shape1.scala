
	
	import java.awt.geom.Ellipse2D
	import java.awt.geom.Rectangle2D
	
	case class Shape(shape : java.awt.Shape) extends Graphic {
	  def render(gc : GraphicsContext) {
	    gc.drawShape(shape)
	  }
	}
	
	object Circle {
	  // (x, y) is at the center
	  def apply(x : Double, y : Double, radius : Double) =
	    Shape(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius))
	}
	
	object Ellipse {
	  // (x, y) is at the upper-left of the bounding box
	  def apply(x : Double, y : Double, xDiam : Double, yDiam : Double) =
	    Shape(new Ellipse2D.Double(x, y, xDiam, yDiam))
	}
	
	object Rectangle {
	  // (x, y) is at the upper-left
	  def apply(x : Double, y : Double, width : Double, height : Double) =
	    Shape(new Rectangle2D.Double(x, y, width, height))
	}
	
	object Square {
	  // (x, y) is at the center
	  def apply(x : Double, y : Double, width : Double) =
	    Shape(new Rectangle2D.Double(x - width / 2, y - width / 2, width, width))
	}
	
	object Polygon {
	  // TODO simulate a Polygon with GeneralPath, since Polygon only takes ints
	  def apply(points : (Double, Double)*) = error("Not implemented")
	}